package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.repository.UsersRepository;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserRepositoryImpl implements UsersRepository{
    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public List<Users> find(Integer Id) {
        return defaultProcedureRepo.getWithType("public.cfn_get_users", new Object[][] {
                { Integer.class, Id, "p_id" },
        }, Users.class);
    }
}
