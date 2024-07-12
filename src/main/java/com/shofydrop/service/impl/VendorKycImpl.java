package com.shofydrop.service.impl;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.entity.Users;
import com.shofydrop.entity.VendorKyc;
import com.shofydrop.enumerated.UserType;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.UsersRepository;
import com.shofydrop.repository.VendorKycRepository;
import com.shofydrop.service.VendorKycService;
import com.shofydrop.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class VendorKycImpl implements VendorKycService {

    @Autowired
    private VendorKycRepository vendorKycRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private FileUtils fileUtils; // Autowire FileUtils component

    private static final Logger log = LoggerFactory.getLogger(VendorKycImpl.class);

    @Override
    public VendorKyc save(Long userId, VendorKyc vendorKyc, MultipartFile frontImageFile, MultipartFile backImageFile) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

            if (user.getUserType() != UserType.VENDOR || user.getUserType() != UserType.DELIVERY_BOY) {
                throw new IllegalStateException("User is not authorized to submit vendor KYC");
            }

            // Handle file uploads
            if (frontImageFile != null && !frontImageFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(frontImageFile.getOriginalFilename());
                vendorKyc.setDocumentImageFront(fileName);
                fileUtils.saveFile(frontImageFile, fileName); // Save front image file
            }

            if (backImageFile != null && !backImageFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(backImageFile.getOriginalFilename());
                vendorKyc.setDocumentImageBack(fileName);
                fileUtils.saveFile(backImageFile, fileName); // Save back image file
            }

            vendorKyc.setUsers(user); // Set the user for vendor KYC
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
        ResponseDto responseDto = new ResponseDto();
        try {
            log.info("Fetching all Vendor KYC records");
            return vendorKycRepository.findAll();
        } catch (RuntimeException e) {
            log.error("Error fetching Vendor KYC records: " + e.getMessage());
            throw new RuntimeException("Internal Server Error: " + e.getMessage(), e);
        }
    }

    @Override
    public VendorKyc findById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
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

    @Override
    public void delete(Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            vendorKycRepository.deleteById(id);
        } catch (RuntimeException e) {
            log.error("Error deleting Vendor KYC: " + e.getMessage());
            throw new RuntimeException("Internal Server Error: " + e.getMessage(), e);
        }
    }

    @Override
    public VendorKyc update(Long id, VendorKyc vendorKyc) {
        ResponseDto responseDto = new ResponseDto();
        try {
            VendorKyc existingKyc = vendorKycRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Vendor KYC not found with ID: " + id));

            existingKyc.setDocumentType(vendorKyc.getDocumentType());
            existingKyc.setDocumentNumber(vendorKyc.getDocumentNumber());
            existingKyc.setDocumentImageFront(vendorKyc.getDocumentImageFront());
            existingKyc.setDocumentImageBack(vendorKyc.getDocumentImageBack());
            existingKyc.setUpdatedAt(Timestamp.from(Instant.now()));

            return vendorKycRepository.save(existingKyc);
        } catch (ResourceNotFoundException e) {
            log.error("Vendor KYC not found: " + e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            log.error("Error updating Vendor KYC: " + e.getMessage());
            throw new RuntimeException("Internal Server Error: " + e.getMessage(), e);
        }
    }
}
