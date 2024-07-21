package com.msp.shofydrop.authentication.controller;

import com.msp.shofydrop.authentication.entity.VendorKyc;
import com.msp.shofydrop.authentication.service.VendorKycService;
import com.msp.shofydrop.exception.EmailNotVerifiedException;
import com.msp.shofydrop.exception.ResourceNotFoundException;
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

    @Autowired
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
        log.info("Inside addVendorKyc method of VendorKycController (authentication package)");

        // Validate inputs
//        if (StringUtils.isEmpty(documentType) || StringUtils.isEmpty(documentNumber)) {
//            return ResponseEntity.badRequest().body("Document type and number are required.");
//        }
        if (documentImageFront.isEmpty()) {
            return ResponseEntity.badRequest().body("Document front image is required.");
        }
        if (documentImageBack.isEmpty()) {
            return ResponseEntity.badRequest().body("Document back image is required.");
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

            return ResponseEntity.status(HttpStatus.CREATED).body("Vendor KYC form submitted successfully.");
        } catch (EmailNotVerifiedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error submitting vendor KYC form: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting vendor KYC form.");
        }
    }

    @GetMapping("/getAllVendorKyc")
    public ResponseEntity<List<VendorKyc>> getAllKyc() {
        log.info("Inside getAllKyc method of VendorKycController (authentication package)");
        List<VendorKyc> kycList = vendorKycService.findAll();
        return ResponseEntity.ok(kycList);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateKyc(
            @PathVariable Long id,
            @RequestParam("documentType") String documentType,
            @RequestParam("documentNumber") String documentNumber,
            @RequestParam("documentImageBack") MultipartFile documentImageBack,
            @RequestParam("documentImageFront") MultipartFile documentImageFront) {
        log.info("Inside updateKyc method of VendorKycController (authentication package)");

        try {
            // Validate inputs
//            if (StringUtils.isEmpty(documentType) || StringUtils.isEmpty(documentNumber)) {
//                return ResponseEntity.badRequest().body("Document type and number are required.");
//            }
            if (documentImageFront.isEmpty()) {
                return ResponseEntity.badRequest().body("Document front image is required.");
            }
            if (documentImageBack.isEmpty()) {
                return ResponseEntity.badRequest().body("Document back image is required.");
            }

            VendorKyc updatedVendorKyc = new VendorKyc();
            updatedVendorKyc.setDocumentType(documentType);
            updatedVendorKyc.setDocumentNumber(documentNumber);

            // Delegate update operation to service layer
//            VendorKyc updatedKyc = vendorKycService.update(id, updatedVendorKyc, documentImageFront, documentImageBack);

            return ResponseEntity.status(HttpStatus.CREATED).body("Vendor KYC form updated successfully.");
        } catch (ResourceNotFoundException e) {
            log.error("Vendor KYC not found: " + e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            log.error("Internal Server Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error.");
        }
    }

    @GetMapping("/vendorKyc/{id}")
    public ResponseEntity<VendorKyc> vendorKyc(@PathVariable Long id) {
        log.info("Inside vendorKyc method of VendorKycController (authentication package)");

        VendorKyc vendorKyc = vendorKycService.findById(id);
        log.info("Get VendorKyc: {}", vendorKyc);
        return ResponseEntity.status(HttpStatus.OK).body(vendorKyc);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVendorKyc(@PathVariable Long id) {
        log.info("Inside deleteVendorKyc method of VendorKycController (authentication package)");

        try {
            vendorKycService.deleteById(id);
            return ResponseEntity.ok("Vendor KYC with id " + id + " deleted successfully.");
        } catch (RuntimeException e) {
            log.error("Failed to delete Vendor KYC: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete Vendor KYC: " + e.getMessage());
        }
    }
}
