package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.UserDetails;
import com.msp.shofydrop.authentication.repository.UserDetailsRepo;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserDetailsRepoImpl implements UserDetailsRepo {
    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public Optional<UserDetails> findByUserId(Long userId) {
        List<UserDetails> userDetails = defaultProcedureRepo.getWithType("authentication.cfn_get_user_details", new Object[][]{
                {Long.class, userId, "p_id"},
        }, UserDetails.class);
        if (userDetails.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userDetails.get(0));
        }
    }

    @Override
    public String saveUserDetails(UserDetails userDetails) {
        Object id[] = defaultProcedureRepo.executeWithType("authentication.cfn_add_edit_user_details", new Object[][]{
                {String.class, userDetails.getIsEmailVerified(), "p_is_emaolverified"}
        });
        return "";
    }
}
