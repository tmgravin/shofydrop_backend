package com.shofydrop.service;


import com.shofydrop.entity.VendorKyc;

import java.util.List;

public interface VendorKycService {
    VendorKyc save(Long userId, VendorKyc vendorKyc);

    List<VendorKyc> findAll();

    VendorKyc findById(Long id);

    void delete(Long id);


    VendorKyc update(Long id, VendorKyc vendorKyc);

}
