package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.PasswordResetCode;
import com.msp.shofydrop.authentication.repository.PasswordResetRepo;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class PasswordResetRepoImpl implements PasswordResetRepo {
    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public String saveCode(PasswordResetCode code) {
        Object id[] = defaultProcedureRepo.executeWithType("authentication.cfn_add_edit_password_reset_code", new Object[][]{
                {Integer.class, code.getCode(), "p_code"},
                {String.class, code.getIsVerified(), "p_id_verified"},
                {Timestamp.class, code.getExpiredAt(), "p_expired_at"},
                {Long.class, code.getId(), "p_id"},
                {Long.class, code.getUserId(), "p_user_id"}
        });
        return (String) id[0].toString();
    }

    @Override
    public Optional<PasswordResetCode> findByCode(int code) {
        List<PasswordResetCode> passwordResetCodeList = defaultProcedureRepo.getWithType("authentication.cfn_get_password_reset_by_code", new Object[][]{
                {Integer.class, code, "p_code"}
        }, PasswordResetCode.class);
        if (passwordResetCodeList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(passwordResetCodeList.get(0));
        }
    }

    @Override
    public Optional<PasswordResetCode> findByIsVerified(String isVerified) {
        List<PasswordResetCode> passwordResetCodeList = defaultProcedureRepo.getWithType("authentication.cfn_get_password_reset_by_isVerified", new Object[][]{
                {String.class, isVerified, "p_isVerified"}
        }, PasswordResetCode.class);
        if (passwordResetCodeList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(passwordResetCodeList.get(0));
        }

    }

    @Override
    public List<PasswordResetCode> findAllCodesByUserId(Long userID) {
        return defaultProcedureRepo.getWithType("authentication.cfn_get_password_reset_code", new Object[][]{
                {Long.class, userID, "p_user_id"}
        }, PasswordResetCode.class);
    }

    @Override
    public void deletePasswordResetCode(Long userId) {
        Object id[] = defaultProcedureRepo.executeWithType("authentication.cfn_delete_password_reset_code", new Object[][]{
                {Long.class, userId, "p_user_id"}
        });
    }

}
