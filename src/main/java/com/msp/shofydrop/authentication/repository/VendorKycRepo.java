package com.msp.shofydrop.authentication.repository;


import com.msp.shofydrop.authentication.entity.VendorKyc;

import java.util.List;

public interface VendorKycRepo {
    List<VendorKyc> getKyc(Long id);

    String saveKyc(VendorKyc kyc);
}
