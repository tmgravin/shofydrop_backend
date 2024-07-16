package com.shofydrop.service.impl;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.entity.UserDetails;
import com.shofydrop.entity.Users;
import com.shofydrop.entity.VendorKyc;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.UserDetailsRepository;
import com.shofydrop.repository.UsersRepository;
import com.shofydrop.repository.VendorKycRepo;
import com.shofydrop.service.VendorKycService;
import com.shofydrop.utils.FileUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VendorKycImpl implements VendorKycService {

    @Autowired
    private VendorKycRepo vendorKycRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private FileUtils fileUtils; // Autowire FileUtils component

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    private static final Logger log = LoggerFactory.getLogger(VendorKycImpl.class);

    @Override
    public VendorKyc save(Long userId, VendorKyc vendorKyc, MultipartFile frontImageFile, MultipartFile backImageFile) {
        ResponseDto responseDto = new ResponseDto();
        try {
            // Retrieve the user by ID, throw an exception if not found
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

            // Handle front image file upload if provided
            if (frontImageFile != null && !frontImageFile.isEmpty()) {
                String fileName = fileUtils.generateFileName(frontImageFile); // Generate unique filename
                vendorKyc.setDocumentImageFront(fileName);
                fileUtils.saveFile(frontImageFile, fileName); // Save front image file
            }

            // Handle back image file upload if provided
            if (backImageFile != null && !backImageFile.isEmpty()) {
                String fileName = fileUtils.generateFileName(backImageFile); // Generate unique filename
                vendorKyc.setDocumentImageBack(fileName);
                fileUtils.saveFile(backImageFile, fileName); // Save back image file
            }

            // Set the user for the VendorKyc entity
            vendorKyc.setUsers(user);

            // Retrieve UserDetails and update KYC status
            UserDetails userDetails = userDetailsRepository.findByUsersId(userId)
                    .orElseThrow(() -> new IllegalArgumentException("UserDetails not found for user with ID: " + userId));
            userDetails.setIsKycCompleted('Y');
            userDetailsRepository.save(userDetails);

            // Save VendorKyc entity
            return vendorKycRepository.save(vendorKyc);
        } catch (IllegalArgumentException e) {
            log.error("User not Found: " + userId);
            responseDto.setStatus(HttpStatus.NOT_FOUND);
            throw new IllegalArgumentException("User not Found: " + e.getMessage());
        } catch (IOException e) {
            log.error("File IO Error: " + e);
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDto.setMessage("File IO Error: " + e.getMessage());
            throw new RuntimeException("Internal Server Error: " + e.getMessage(), e);
        }
    }

    @Override
    public List<VendorKyc> findAll() {
        // Retrieve all VendorKyc entities
        return vendorKycRepository.findAll();
    }

    @Override
    public VendorKyc findById(Long id) {
        try {
            // Retrieve VendorKyc entity by ID, throw an exception if not found
            return vendorKycRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Vendor KYC not found with ID: " + id));
        } catch (ResourceNotFoundException e) {
            log.error("Vendor KYC not found: " + e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            log.error("Error finding Vendor KYC: " + e.getMessage());
            throw new RuntimeException("Internal Server Error: " + e.getMessage(), e);
        }
    }

    @Transactional
    public VendorKyc update(Long id, VendorKyc updatedVendorKyc,
                            MultipartFile frontImageFile, MultipartFile backImageFile) {
        try {
            // Retrieve existing VendorKyc entity by ID, throw an exception if not found
            VendorKyc existingVendorKyc = vendorKycRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Vendor KYC not found with ID: " + id));

            // Handle updating the front image file if provided
            if (frontImageFile != null && !frontImageFile.isEmpty()) {
                if (existingVendorKyc.getDocumentImageFront() != null) {
                    fileUtils.deleteFileIfExists(existingVendorKyc.getDocumentImageFront()); // Delete existing file
                }
                String frontFileName = fileUtils.generateFileName(frontImageFile);
                fileUtils.saveFile(frontImageFile, frontFileName);
                existingVendorKyc.setDocumentImageFront(frontFileName);
            }

            // Handle updating the back image file if provided
            if (backImageFile != null && !backImageFile.isEmpty()) {
                if (existingVendorKyc.getDocumentImageBack() != null) {
                    fileUtils.deleteFileIfExists(existingVendorKyc.getDocumentImageBack()); // Delete existing file
                }
                String backFileName = fileUtils.generateFileName(backImageFile);
                fileUtils.saveFile(backImageFile, backFileName);
                existingVendorKyc.setDocumentImageBack(backFileName);
            }

            // Update other fields of VendorKyc entity
            existingVendorKyc.setDocumentType(updatedVendorKyc.getDocumentType());
            existingVendorKyc.setDocumentNumber(updatedVendorKyc.getDocumentNumber());
            // Add more field updates as needed

            // Save the updated VendorKyc entity
            return vendorKycRepository.save(existingVendorKyc);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException("Failed to perform file operation: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error updating Vendor KYC: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            // Delete VendorKyc entity by ID
            vendorKycRepository.deleteById(id);
        } catch (RuntimeException e) {
            log.error("Error deleting Vendor KYC: " + e.getMessage());
            throw new RuntimeException("Internal Server Error: " + e.getMessage(), e);
        }
    }
}
