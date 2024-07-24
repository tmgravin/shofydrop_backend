package com.msp.shofydrop.authentication.serviceImpl;

import com.msp.shofydrop.authentication.entity.UserDetails;
import com.msp.shofydrop.authentication.entity.VendorKyc;
import com.msp.shofydrop.authentication.repository.UserDetailsRepo;
import com.msp.shofydrop.authentication.repository.VndorKycRepo;
import com.msp.shofydrop.authentication.service.VendorKycService;
import com.msp.shofydrop.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class VendorKycServiceImpl implements VendorKycService {

    @Autowired
    private VndorKycRepo vndorKycRepo;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Override
    public List<VendorKyc> get(Long id) {
        return vndorKycRepo.getKyc(id);
    }

    @Override
    public String saveKyc(VendorKyc kyc, MultipartFile documentImageFront, MultipartFile documentImageBack) throws Exception {
        try {
            UserDetails userDetails = userDetailsRepo.get(kyc.getVendorId()).get(0);

            if (userDetails == null) {
                throw new ResourceNotFoundException("UserDetails not exist: " + kyc.getVendorId());
            }
            userDetails.setIsKycCompleted('Y');
            userDetailsRepo.saveUserDetails(userDetails);
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
            return vndorKycRepo.saveKyc(kyc);
        } catch (Exception e) {
            throw new Exception("Failed to save KYC: " + e.getMessage(), e);
        }
    }
}
