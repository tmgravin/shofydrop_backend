package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.VendorKyc;
import com.msp.shofydrop.authentication.repository.VndorKycRepo;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendorKycRepoImpl implements VndorKycRepo {
    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;


    @Override
    public List<VendorKyc> getKyc(Long id) {
        return defaultProcedureRepo.getWithType("authentication.cfn_add_edit_vendor_kyc", new Object[][]{
                {Long.class, id, "p_id"},
        }, VendorKyc.class);
    }

    @Override
    public String saveKyc(VendorKyc kyc) {
        Object id[] = defaultProcedureRepo.executeWithType("authentication.cfn_add_edit_vendor_kyc", new Object[][]{
                {Long.class, kyc.getVendorId(), "p_vendor_id"},
                {String.class, kyc.getDocumentType(), "p_document_type"},
                {String.class, kyc.getDocumentNumber(), "p_document_number"},
                {String.class, kyc.getDocumentImageFront(), "p_document_image_front"},
                {String.class, kyc.getDocumentImageBack(), "p_document_image_back"}
        });
        return (String) id[0].toString();
    }
}
