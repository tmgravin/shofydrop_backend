package com.shofydrop.service;


import com.shofydrop.entity.VendorKyc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VendorKycService {
    VendorKyc save(Long userId, VendorKyc vendorKyc, MultipartFile frontImageFile, MultipartFile backImageFile);

    List<VendorKyc> findAll();

    VendorKyc findById(Long id);

    void delete(Long id);

    VendorKyc update(Long id, VendorKyc updatedVendorKyc,
                     MultipartFile frontImageFile, MultipartFile backImageFile);
}
