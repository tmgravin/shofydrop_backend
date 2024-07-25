package com.msp.shofydrop.authentication.controller;

import com.msp.shofydrop.authentication.entity.VendorKyc;
import com.msp.shofydrop.authentication.service.VendorKycService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/auth/kyc")
public class VendorKycController {

    @Autowired
    private VendorKycService vendorKycService;


    private static final Logger log = LoggerFactory.getLogger(VendorKycController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createVendorKyc(
            @RequestParam("vendorId") Long vendorId,
            @RequestParam("documentNumber") String documentNumber,
            @RequestParam("documentType") String documentType,
            @RequestParam("documentImageFront") MultipartFile documentImageFront,
            @RequestParam("documentImageBack") MultipartFile documentImageBack) {

        try {
            VendorKyc kyc = new VendorKyc();
            kyc.setVendorId(vendorId);
            kyc.setDocumentNumber(documentNumber);
            kyc.setDocumentType(documentType);

            String response = vendorKycService.saveKyc(kyc, documentImageFront, documentImageBack);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error occurred while saving vendor KYC", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getkyc(@RequestParam(name = "id", required = false) Long id) {
        log.info("Inside getKyc method of VendorKycController (authentication)");
        try {
            return ResponseEntity.ok().body(vendorKycService.get(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }
}
