package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.repository.UserRepository;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public List<Users> getUsers(Long id) {
        return defaultProcedureRepo.getWithType("authentication.cfn_get_users", new Object[][]{
                {Long.class, id, "p_id"},
        }, Users.class);
    }

    @Override
    public String saveUser(Users user) {
        Object id[] = defaultProcedureRepo.executeWithType("authentication.cfn_add_edit_users", new Object[][]{
                {Long.class, user.getId(), "p_id"},
                {String.class, user.getName(), "p_name"},
                {String.class, user.getEmail(), "p_email"},
                {String.class, user.getPassword(), "p_password"},
                {String.class, user.getUserType(), "p_user_type"},
                {String.class, user.getLoginType(), "p_login_type"},
        });
        return (String) id[0].toString();
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        List<Users> usersList = defaultProcedureRepo.getWithType("authentication.cfn_get_users_by_email", new Object[][]{
                {String.class, email,"p_email"}
        }, Users.class);

        if (usersList.isEmpty()){
            return Optional.empty();
        }else {
            return Optional.of(usersList.get(0));
        }
    }
}
