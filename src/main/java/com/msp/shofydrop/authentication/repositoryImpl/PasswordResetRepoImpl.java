package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.PasswordResetCode;
import com.msp.shofydrop.authentication.repository.PasswordResetRepo;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PasswordResetRepoImpl implements PasswordResetRepo {
    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public String saveCode(PasswordResetCode code) {
        Object id[] = defaultProcedureRepo.executeWithType("authentication.cfn_add_edit_password_reset_code", new Object[][]{

        });
        return (String) id[0].toString();
    }

    @Override
    public Optional<PasswordResetCode> findByCode(int code) {
        List<PasswordResetCode> passwordResetCodeList = defaultProcedureRepo.getWithType("authentication.cfn_get_password_reset_by_code", new Object[][]{
                {Integer.class, code, ""}
        }, PasswordResetCode.class);
        if (passwordResetCodeList.isEmpty()) {
            return Optional.empty();
        }else{
            return Optional.of(passwordResetCodeList.get(0));
        }
    }
}
