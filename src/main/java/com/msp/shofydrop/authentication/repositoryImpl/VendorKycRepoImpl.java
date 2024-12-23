package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.VendorKyc;
import com.msp.shofydrop.authentication.repository.VendorKycRepo;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendorKycRepoImpl implements VendorKycRepo {
    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;


    @Override
    public List<VendorKyc> getKyc(Long vendorId) {
        return defaultProcedureRepo.getWithType("authentication.cfn_get_vendor_kyc", new Object[][]{
                {Long.class, vendorId, "p_vendor_id"},
        }, VendorKyc.class);
    }

    @Override
    public String saveKyc(VendorKyc kyc) {
        Object[] vendorId = defaultProcedureRepo.executeWithType("authentication.cfn_add_edit_vendor_kyc", new Object[][]{
                {Long.class, kyc.getVendorId(), "p_vendor_id"},
                {String.class, kyc.getDocumentType(), "p_document_type"},
                {String.class, kyc.getDocumentNumber(), "p_document_number"},
                {String.class, kyc.getDocumentImageFront(), "p_document_image_front"},
                {String.class, kyc.getDocumentImageBack(), "p_document_image_back"}
        });
        return (String) vendorId[0].toString();
    }
}
