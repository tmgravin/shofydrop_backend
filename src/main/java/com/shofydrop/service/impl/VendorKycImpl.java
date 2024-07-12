package com.shofydrop.service.impl;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.entity.Users;
import com.shofydrop.entity.VendorKyc;
import com.shofydrop.enumerated.UserType;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.UsersRepository;
import com.shofydrop.repository.VendorKycRepository;
import com.shofydrop.service.VendorKycService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class VendorKycImpl implements VendorKycService {

    @Autowired
    private VendorKycRepository vendorKycRepository;

    @Autowired
    private UsersRepository usersRepository;

    private static final Logger log = LoggerFactory.getLogger(VendorKyc.class);


    @Override
    public VendorKyc save(@PathVariable Long userId, VendorKyc vendorKyc) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

            if (user.getUserType() != UserType.VENDOR) {
                throw new IllegalStateException("User is not authorized to submit vendor KYC");
            }
            vendorKyc.setUsers(user);
            return vendorKycRepository.save(vendorKyc);
        } catch (IllegalArgumentException e) {
            log.error("User not Found: " + userId);
            responseDto.setStatus(HttpStatus.NOT_FOUND);
            throw new IllegalArgumentException("User not Found" + e);
        } catch (RuntimeException e) {
            log.error("Internal Server Error" + e);
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDto.setMessage("Internal Server Error" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<VendorKyc> findAll() {
        ResponseDto response = new ResponseDto();
        try {
            log.info("Vendor KYC successfully fetched");
            response.setStatus(HttpStatus.OK);
            return vendorKycRepository.findAll();
        } catch (RuntimeException e) {
            log.error("Internal Server Error");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new RuntimeException("Internal Server Error" + e);
        }
    }


    @Override
    public VendorKyc findById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            return vendorKycRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("VendorKYC Not Found" + id));
        } catch (RuntimeException e) {
            log.error("Internal Server error");
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDto.setMessage("Internal Server Error" + e);
            throw new RuntimeException("Internal Server Error", e);
        }
    }

    @Override
    public void delete(Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            log.info("KYC deleted successfully" + id);
            responseDto.setStatus(HttpStatus.OK);
            responseDto.setMessage("KYC deleted Successfully");
        } catch (EntityNotFoundException e) {
            log.error("Not Found" + e);
            responseDto.setStatus(HttpStatus.NOT_FOUND);
            throw new EntityNotFoundException("Not Found");
        } catch (RuntimeException e) {
            log.error("Internal Server Error" + e);
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new RuntimeException("Internal Server Error" + e);
        }
    }

    @Override
    public VendorKyc update(Long id, VendorKyc vendorKyc) {
        ResponseDto responseDto = new ResponseDto();
        try {
            VendorKyc vendorKyc1 = vendorKycRepository.findById(id).orElseThrow(() -> new RuntimeException("KYC Not Found"));
            vendorKyc1.setDocumentType(vendorKyc.getDocumentType());
            vendorKyc1.setDocumentNumber(vendorKyc.getDocumentNumber());
            vendorKyc1.setDocumentImageFront(vendorKyc.getDocumentImageFront());
            vendorKyc1.setDocumentImageBack(vendorKyc.getDocumentImageBack());
            vendorKyc1.setUpdatedAt(Timestamp.from(Instant.now()));
            return vendorKycRepository.save(vendorKyc);
        } catch (EntityNotFoundException e) {
            log.error("Not Found" + e);
            responseDto.setStatus(HttpStatus.NOT_FOUND);
            throw new EntityNotFoundException("Not Found" + e);
        } catch (RuntimeException e) {
            log.error("Internal Server Error" + e);
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new RuntimeException("Internal Server Error" + e);
        }

    }
}
