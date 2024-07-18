package com.msp.shofydrop.service;


import com.msp.shofydrop.entity.VendorKyc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VendorKycService {
    VendorKyc save(Long userId, VendorKyc vendorKyc, MultipartFile frontImageFile, MultipartFile backImageFile);

    List<VendorKyc> findAll();

    VendorKyc findById(Long id);

    void deleteById(Long id);

    VendorKyc update(Long id, VendorKyc updatedVendorKyc,
                     MultipartFile frontImageFile, MultipartFile backImageFile);

}
