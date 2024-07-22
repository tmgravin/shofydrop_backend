package com.msp.shofydrop.authentication.repositoryImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.repository.UserRepository;
import com.msp.shofydrop.database.DefaultProcedureRepo;

@Repository
public class UserRepositoryImpl implements UserRepository
{
	@Autowired
	private DefaultProcedureRepo defaultProcedureRepo;
	
	@Override
	public List<Users> getUsers(Integer id)
	{
		return defaultProcedureRepo.getWithType("public.cfn_get_users", new Object[][] {
			{ Integer.class, id, "p_id" },
		}, Users.class);
	}
	
	@Override
	public Integer saveUser(Users user)
	{
		Object id[] = defaultProcedureRepo.executeWithType("public.cfn_add_edit_users", new Object[][] {
			{ Integer.class, user.getId(), "p_id" },
			{ String.class, user.getName(), "p_name" },
			{ String.class, user.getEmail(), "p_email" },
			{ String.class, user.getPassword(), "p_password" },
			{ String.class, user.getUserType(), "p_user_type" },
			{ String.class, user.getLoginType(), "p_login_type" },
		});
		return (Integer) id[0];
	}
}
