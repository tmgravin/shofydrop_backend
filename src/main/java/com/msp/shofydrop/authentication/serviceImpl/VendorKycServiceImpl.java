package com.msp.shofydrop.authentication.serviceImpl;

import com.msp.shofydrop.authentication.entity.VendorKyc;
import com.msp.shofydrop.authentication.repository.UserDetailsRepo;
import com.msp.shofydrop.authentication.repository.VendorKycRepo;
import com.msp.shofydrop.authentication.service.VendorKycService;
import com.msp.shofydrop.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class VendorKycServiceImpl implements VendorKycService {

    @Autowired
    private VendorKycRepo vendorKycRepo;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Override
    public List<VendorKyc> get(Long id) {
        return vendorKycRepo.getKyc(id);
    }

    @Override
    public String saveKyc(VendorKyc kyc, MultipartFile documentImageFront, MultipartFile documentImageBack) throws Exception {
        try {
//
//            UserDetails userDetails = userDetailsRepo.get(kyc.getVendorId()).get(0);
//            userDetails.setIsKycCompleted('Y');
//            userDetailsRepo.saveUserDetails(userDetails);

            // Save files and set file paths in the KYC object
            String frontFileName = fileUtils.generateFileName(documentImageFront);
            String backFileName = fileUtils.generateFileName(documentImageBack);

            fileUtils.saveFile(documentImageFront, frontFileName);
            fileUtils.saveFile(documentImageBack, backFileName);

            kyc.setDocumentImageFront(frontFileName);
            kyc.setDocumentImageBack(backFileName);


            // Save KYC details to the database
            return vendorKycRepo.saveKyc(kyc);
        } catch (Exception e) {
            throw new Exception("Failed to save KYC: " + e.getMessage(), e);
        }
    }
}
