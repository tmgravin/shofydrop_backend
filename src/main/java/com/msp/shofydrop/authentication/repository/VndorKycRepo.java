package com.msp.shofydrop.authentication.repository;


import com.msp.shofydrop.authentication.entity.VendorKyc;

import java.util.List;

public interface VndorKycRepo {
    List<VendorKyc> getKyc(Long id);

    String saveKyc(VendorKyc kyc);
}
