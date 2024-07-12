package com.shofydrop.controller;

import com.shofydrop.entity.VendorKyc;
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

    @GetMapping("/getVendorKycById/{id}")
    public ResponseEntity<VendorKyc> findById(@PathVariable Long id) {
        VendorKyc vendorKyc = vendorKycService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(vendorKyc);
    }

    @PutMapping("/update/id")
    public String updateVendorKyc(@PathVariable Long id, @RequestBody VendorKyc vendorKyc) {
        vendorKycService.update(id, vendorKyc);
        return "Vendor Kyc Update";
    }
}
