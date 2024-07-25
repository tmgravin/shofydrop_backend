package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.UserDetails;
import com.msp.shofydrop.authentication.repository.UserDetailsRepo;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserdetailsRepoImpl implements UserDetailsRepo {

    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public Optional<UserDetails> findByUserId(Long userId) {
        List<UserDetails> userDetails = defaultProcedureRepo.getWithType("authentication.cfn_get_user_details", new Object[][]{
                {Long.class, userId, "p_id"},
        }, UserDetails.class);
        if(userDetails.isEmpty()){
            return Optional.empty();
        }else{
            return Optional.of(userDetails.get(0));
        }
    }

    @Override
    public String saveUserDetails(UserDetails userDetails) {
        Object userId[] = defaultProcedureRepo.executeWithType("authentication.cfn_add_edit_user_details", new Object[][]{
                {Long.class, userDetails.getUserId(), "p_user_id"},
                {String.class, userDetails.getIsEmailVerified(), "p_is_email_verified"},
                {String.class, userDetails.getIsKycApproved(), "p_is_kyc_approved"},
                {String.class, userDetails.getIsKycCompleted(), "p_is_kyc_completed"}
        });
        return (String) userId[0].toString();
    }
}
