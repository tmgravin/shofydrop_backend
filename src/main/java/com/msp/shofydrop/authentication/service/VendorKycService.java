package com.msp.shofydrop.authentication.service;

import com.msp.shofydrop.authentication.entity.VendorKyc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VendorKycService {

    List<VendorKyc> get(Long vendorId);

    String saveKyc(VendorKyc kyc, MultipartFile documentImageFront, MultipartFile documentImageBack);
}
