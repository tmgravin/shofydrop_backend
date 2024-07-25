package com.msp.shofydrop.authentication.serviceImpl;

import com.msp.shofydrop.authentication.entity.UserDetails;
import com.msp.shofydrop.authentication.entity.VendorKyc;
import com.msp.shofydrop.authentication.repository.UserDetailsRepo;
import com.msp.shofydrop.authentication.repository.VndorKycRepo;
import com.msp.shofydrop.authentication.service.VendorKycService;
import com.msp.shofydrop.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VendorKycServiceImpl implements VendorKycService {

    private static final Logger log = LoggerFactory.getLogger(VendorKycServiceImpl.class);

    @Autowired
    private VndorKycRepo vndorKycRepo;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Transactional
    @Override
    public List<VendorKyc> get(Long vendorId) {
        log.info("Inside get method of VendorKycServiceImpl (authentication)");
        return vndorKycRepo.getKyc(vendorId);
    }

    @Transactional
    @Override
    public String saveKyc(VendorKyc kyc, MultipartFile documentImageFront, MultipartFile documentImageBack) {
        log.info("Inside saveKyc method of VendorKycServiceImpl");
        try {
            // Fetch existing KYC details if they exist
            log.info("Fetching existing KYC details for vendorId: {}", kyc.getVendorId());
            List<VendorKyc> existingKycList = vndorKycRepo.getKyc(kyc.getVendorId());
            VendorKyc existingKyc = (existingKycList == null || existingKycList.isEmpty()) ? null : existingKycList.get(0);

            // Delete existing image files if new ones are provided
            if (existingKyc != null) {
                if (documentImageFront != null && existingKyc.getDocumentImageFront() != null) {
                    log.info("Deleting existing front document image: {}", existingKyc.getDocumentImageFront());
                    fileUtils.deleteFileIfExists(existingKyc.getDocumentImageFront());
                }

                if (documentImageBack != null && existingKyc.getDocumentImageBack() != null) {
                    log.info("Deleting existing back document image: {}", existingKyc.getDocumentImageBack());
                    fileUtils.deleteFileIfExists(existingKyc.getDocumentImageBack());
                }
            }

            // Save new files and set file paths in the KYC object
            if (documentImageFront != null) {
                log.info("Saving new front document image");
                String frontFileName = fileUtils.generateFileName(documentImageFront);
                fileUtils.saveFile(documentImageFront, frontFileName);
                kyc.setDocumentImageFront(frontFileName);
            }

            if (documentImageBack != null) {
                log.info("Saving new back document image");
                String backFileName = fileUtils.generateFileName(documentImageBack);
                fileUtils.saveFile(documentImageBack, backFileName);
                kyc.setDocumentImageBack(backFileName);
            }

            // Save KYC details to the database
            log.info("Saving KYC details to the database");
            String result = vndorKycRepo.saveKyc(kyc);

            // Update UserDetails to mark KYC as completed
//            log.info("Updating UserDetails to mark KYC as completed for vendorId: {}", kyc.getVendorId());
            List<UserDetails> userDetailsList = userDetailsRepo.get(kyc.getVendorId());
            if (!userDetailsList.isEmpty()) {
                UserDetails userDetails = userDetailsList.get(0);
                userDetails.setIsKycCompleted("Y");
                userDetailsRepo.saveUserDetails(userDetails);
            }

            return result;
        } catch (Exception e) {
            log.error("Failed to save KYC", e);
            return "";
        }
    }

}
