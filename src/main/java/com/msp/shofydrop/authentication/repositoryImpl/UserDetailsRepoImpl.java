package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.UserDetails;
import com.msp.shofydrop.authentication.repository.UserDetailsRepo;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailsRepoImpl implements UserDetailsRepo {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsRepoImpl.class);
    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public List<UserDetails> get(Long userId) {
        log.info("Fetching UserDetails for userId: {}", userId);
        return defaultProcedureRepo.getWithType("authentication.cfn_get_user_details", new Object[][]{
                {Long.class, userId, "p_id"}
        }, UserDetails.class);
    }


    @Override
    public String saveUserDetails(UserDetails userDetails) {
        Object[] userId = defaultProcedureRepo.executeWithType("authentication.cfn_add_edit_user_details", new Object[][]{
                {String.class, userDetails.getIsEmailVerified(), "p_is_email_verified"},
                {String.class, userDetails.getIsKycApproved(), "p_is_kyc_approved"},
                {String.class, userDetails.getIsKycCompleted(), "p_is_kyc_completed"},
                {Long.class, userDetails.getUserId(), "p_user_id"}

        });
        return (String) userId[0].toString();
    }
}
