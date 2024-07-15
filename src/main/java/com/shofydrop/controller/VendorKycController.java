package com.shofydrop.controller;

import com.shofydrop.entity.VendorKyc;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.service.VendorKycService;
import jakarta.persistence.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vendor/kyc/api")
public class VendorKycController {

    @Autowired(required = true)
    private VendorKycService vendorKycService;

    @Transient
    private MultipartFile documentImageFront;

    @Transient
    private MultipartFile documentImageBack;

    private static final Logger log = LoggerFactory.getLogger(VendorKycController.class);

    @PostMapping(value = "/submit/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addVendorKyc(
            @PathVariable Long userId,
            @RequestParam("documentType") String documentType,
            @RequestParam("documentNumber") String documentNumber,
            @RequestParam("documentImageBack") MultipartFile documentImageBack,
            @RequestParam("documentImageFront") MultipartFile documentImageFront) {

        // Validate inputs
        if (StringUtils.isEmpty(documentType) || StringUtils.isEmpty(documentNumber)) {
            return ResponseEntity.badRequest().body("Document type and number are required");
        }
        if (documentImageFront.isEmpty()) {
            return ResponseEntity.badRequest().body("Document front image is required");
        }
        if (documentImageBack.isEmpty()) {
            return ResponseEntity.badRequest().body("Document back image is required");
        }

        try {
            VendorKyc vendorKyc = new VendorKyc();
            vendorKyc.setDocumentType(documentType);
            vendorKyc.setDocumentNumber(documentNumber);

            // Handle file uploads
            String frontImageFileName = StringUtils.cleanPath(documentImageFront.getOriginalFilename());
            String backImageFileName = StringUtils.cleanPath(documentImageBack.getOriginalFilename());

            vendorKyc.setDocumentImageFront(frontImageFileName);
            vendorKyc.setDocumentImageBack(backImageFileName);

            vendorKycService.save(userId, vendorKyc, documentImageBack, documentImageFront);

            return ResponseEntity.status(HttpStatus.CREATED).body("Vendor KYC submitted successfully");
        } catch (Exception e) {
            log.error("Error submitting vendor KYC");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting vendor KYC");
        }
    }

    @GetMapping("/getAllVendorKyc")
    public List<VendorKyc> getAllKyc() {
        return vendorKycService.findAll();
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateKyc(
            @PathVariable Long id,
            @RequestParam("documentType") String documentType,
            @RequestParam("documentNumber") String documentNumber,
            @RequestParam("documentImageBack") MultipartFile documentImageBack,
            @RequestParam("documentImageFront") MultipartFile documentImageFront) {

        try {
            // Validate inputs
            if (StringUtils.isEmpty(documentType) || StringUtils.isEmpty(documentNumber)) {
                return ResponseEntity.badRequest().body("Document type and number are required");
            }
            if (documentImageFront.isEmpty()) {
                return ResponseEntity.badRequest().body("Document front image is required");
            }
            if (documentImageBack.isEmpty()) {
                return ResponseEntity.badRequest().body("Document back image is required");
            }

            VendorKyc updatedVendorKyc = new VendorKyc();
            updatedVendorKyc.setDocumentType(documentType);
            updatedVendorKyc.setDocumentNumber(documentNumber);

            // Delegate update operation to service layer
            VendorKyc updatedKyc = vendorKycService.update(id, updatedVendorKyc, documentImageFront, documentImageBack);

            return ResponseEntity.status(HttpStatus.CREATED).body("Vendor KYC Updated Successfully");
        } catch (ResourceNotFoundException e) {
            log.error("Vendor KYC not found: " + e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            log.error("Internal Server Error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @GetMapping("/vendorKyc/{id}")
    public ResponseEntity<VendorKyc> vendorKyc(@PathVariable Long id) {
        VendorKyc vendorKyc = vendorKycService.findById(id);
        log.info("Get VendorKyc: {}", vendorKyc.toString());
        return ResponseEntity.status(HttpStatus.OK).body(vendorKyc);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteKyc(@PathVariable Long id) {
        vendorKycService.delete(id);
        log.info("KYC SuccessFully Deleted");
        return ResponseEntity.status(HttpStatus.OK).body("KYC Successfully Deleted");
    }
}
