package com.shofydrop.controller;

import com.shofydrop.entity.VendorKyc;
import com.shofydrop.service.VendorKycService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor/kyc")
public class VendorKycController {

    @Autowired
    private VendorKycService vendorKycService;

    private static final Logger log = LoggerFactory.getLogger(VendorKycController.class);

    @PostMapping("/submit/{userId}")
    public void addVendorKyc(@PathVariable Long userId, @RequestBody VendorKyc kyc) {
        vendorKycService.save(userId, kyc);
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
