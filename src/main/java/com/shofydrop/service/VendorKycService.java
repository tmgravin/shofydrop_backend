package com.shofydrop.service;


import com.shofydrop.entity.VendorKyc;

public interface VendorKycService {
    VendorKyc save(VendorKyc vendorKyc);

    VendorKyc findById(Long id);

    void delete(Long id);

    VendorKyc update(VendorKyc vendorKyc);
}
