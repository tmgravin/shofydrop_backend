package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.repository.UsersRepository;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UsersRepository {
    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public List<Users> find(Long id) {
        return defaultProcedureRepo.getWithType("public.cfn_get_users", new Object[][]{
                {Long.class, id},
        }, Users.class);
    }
}
