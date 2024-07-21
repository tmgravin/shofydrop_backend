package com.msp.shofydrop.authentication.serviceImpl;

import com.msp.shofydrop.authentication.entity.UserDetails;
import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.entity.VendorKyc;
import com.msp.shofydrop.authentication.repository.UserDetailsRepository;
import com.msp.shofydrop.authentication.repository.UsersRepository;
import com.msp.shofydrop.authentication.repository.VendorKycRepository;
import com.msp.shofydrop.authentication.service.VendorKycService;
import com.msp.shofydrop.dto.ResponseDto;
import com.msp.shofydrop.exception.EmailNotVerifiedException;
import com.msp.shofydrop.exception.ResourceNotFoundException;
import com.msp.shofydrop.utils.FileUtils;
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
public class VendorKycServiceImpl implements VendorKycService {

    private static final Logger log = LoggerFactory.getLogger(VendorKycServiceImpl.class);

    @Autowired
    private VendorKycRepository vendorKycRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private FileUtils fileUtils; // Autowire FileUtils component

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public VendorKyc save(Long userId, VendorKyc vendorKyc, MultipartFile frontImageFile, MultipartFile backImageFile) {
        log.info("Inside save method of VendorKycServiceImpl (authentication package)");

        ResponseDto responseDto = new ResponseDto();
        try {
            Users user = usersRepository.findById(userId).orElseThrow(() ->
                    new IllegalArgumentException("User not found with ID: " + userId));

            // Check if user's email is verified
            UserDetails userDetails = userDetailsRepository.findByUsersId(userId).orElseThrow(() ->
                    new IllegalArgumentException("UserDetails not found for user with ID: " + userId));
            if (userDetails.getIsEmailVerified() == 'N') {
                throw new EmailNotVerifiedException("Email not verified. Please verify your email before requesting for vendor KYC form.");
            }

            // Handle file uploads
            if (frontImageFile != null && !frontImageFile.isEmpty()) {
                String fileName = fileUtils.generateFileName(frontImageFile); // Generate unique filename
                vendorKyc.setDocumentImageFront(fileName);
                fileUtils.saveFile(frontImageFile, fileName); // Save front image file
            }

            if (backImageFile != null && !backImageFile.isEmpty()) {
                String fileName = fileUtils.generateFileName(backImageFile); // Generate unique filename
                vendorKyc.setDocumentImageBack(fileName);
                fileUtils.saveFile(backImageFile, fileName); // Save back image file
            }

            vendorKyc.setUsers(user); // Set the user for vendor KYC

            // Retrieve UserDetails and update KYC status
            userDetails.setIsKycCompleted('Y');
            userDetailsRepository.save(userDetails);

            // Save Vendor KYC
            return vendorKycRepository.save(vendorKyc);
        } catch (IllegalArgumentException e) {
            log.error("User not Found: " + userId, e);
            responseDto.setStatus(HttpStatus.NOT_FOUND);
            throw new IllegalArgumentException("User not Found: " + e.getMessage(), e);
        } catch (EmailNotVerifiedException e) {
            log.error("Email not verified.", e);
            throw e;
        } catch (IOException e) {
            log.error("File IO Error: ", e);
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDto.setMessage("File IO Error: " + e.getMessage());
            throw new RuntimeException("Internal Server Error: " + e.getMessage(), e);
        }
    }

    @Override
    public List<VendorKyc> findAll() {
        log.info("Inside findAll method of VendorKycServiceImpl (authentication package)");
        return vendorKycRepository.findAll();
    }

    @Override
    public VendorKyc findById(Long id) {
        log.info("Inside findById method of VendorKycServiceImpl (authentication package)");
        try {
            return vendorKycRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Vendor KYC not found with ID: " + id));
        } catch (ResourceNotFoundException e) {
            log.error("Vendor KYC not found: " + e.getMessage(), e);
            throw e;
        } catch (RuntimeException e) {
            log.error("Error finding Vendor KYC: " + e.getMessage(), e);
            throw new RuntimeException("Internal Server Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Inside deleteById method of VendorKycServiceImpl (authentication package)");
        vendorKycRepository.deleteById(id);
    }

    @Transactional
    public VendorKyc update(Long id, VendorKyc updatedVendorKyc, MultipartFile frontImageFile, MultipartFile backImageFile) {
        log.info("Inside update method of VendorKycServiceImpl (authentication package)");

        try {
            VendorKyc existingVendorKyc = vendorKycRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Vendor KYC not found with ID: " + id));

            // Delete existing front image file
            if (frontImageFile != null && !frontImageFile.isEmpty()) {
                if (existingVendorKyc.getDocumentImageFront() != null) {
                    fileUtils.deleteFileIfExists(existingVendorKyc.getDocumentImageFront());
                }
                String frontFileName = fileUtils.generateFileName(frontImageFile);
                fileUtils.saveFile(frontImageFile, frontFileName);
                existingVendorKyc.setDocumentImageFront(frontFileName);
            }

            // Delete existing back image file
            if (backImageFile != null && !backImageFile.isEmpty()) {
                if (existingVendorKyc.getDocumentImageBack() != null) {
                    fileUtils.deleteFileIfExists(existingVendorKyc.getDocumentImageBack());
                }
                String backFileName = fileUtils.generateFileName(backImageFile);
                fileUtils.saveFile(backImageFile, backFileName);
                existingVendorKyc.setDocumentImageBack(backFileName);
            }

            // Update other fields of VendorKyc
            existingVendorKyc.setDocumentType(updatedVendorKyc.getDocumentType());
            existingVendorKyc.setDocumentNumber(updatedVendorKyc.getDocumentNumber());
            // Add more field updates as needed

            // Save the updated VendorKyc object
            return vendorKycRepository.save(existingVendorKyc);
        } catch (ResourceNotFoundException e) {
            log.error("Vendor KYC not found: " + e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            log.error("Failed to perform file operation: " + e.getMessage(), e);
            throw new RuntimeException("Failed to perform file operation: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error updating Vendor KYC: " + e.getMessage(), e);
            throw new RuntimeException("Error updating Vendor KYC: " + e.getMessage(), e);
        }
    }
}
