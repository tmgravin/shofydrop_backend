package com.shofydrop.service.impl;

import com.shofydrop.entity.VendorKyc;
import com.shofydrop.repository.VendorKycRepository;
import com.shofydrop.service.VendorKycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorKycImpl implements VendorKycService {

    @Autowired
    private VendorKycRepository vendorKycRepository;

    @Override
    public VendorKyc save(VendorKyc vendorKyc) {
        return vendorKycRepository.save(vendorKyc);
    }

    @Override
    public VendorKyc findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public VendorKyc update(VendorKyc vendorKyc) {
        return vendorKycRepository.save(vendorKyc);
    }
}
