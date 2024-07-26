package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.EmailVerificationToken;
import com.msp.shofydrop.authentication.repository.EmailVerificationRepo;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class EmailVerificationRepoImpl implements EmailVerificationRepo {
    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public String saveToken(EmailVerificationToken token) {
        Object id[] = defaultProcedureRepo.executeWithType("authentication.cfn_add_edit_verification_token", new Object[][]{
                {Timestamp.class, token.getExpiredAt(), "p_expired_at"},
                {Long.class, token.getId(), "p_id"},
                {Long.class, token.getUserId(), "p_user_id"},
                {String.class, token.getToken(), "p_token"}
        });
        return (String) id[0].toString();
    }

    @Override
    public Optional<EmailVerificationToken> findByToken(String token) {
        List<EmailVerificationToken> emailVerificationTokenList = defaultProcedureRepo.getWithType("authentication.cfn_get_verification_by_token", new Object[][]{
                {String.class, token, "p_token"}
        }, EmailVerificationToken.class);
        if (emailVerificationTokenList.isEmpty()){
            return Optional.empty();
        }else{
            return Optional.of(emailVerificationTokenList.get(0));
        }
    }
}
