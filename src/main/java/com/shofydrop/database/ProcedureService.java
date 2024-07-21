package com.shofydrop.database;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shofydrop.entity.Users;

import javax.transaction.Transactional;

@Service
public class ProcedureService
{
	@Autowired
	private DefaultProcedureDao procedure;
	
	@Transactional
	public List<Users> getUsers(BigDecimal id)
	{
		List<Users> u = procedure.getWithType("public.cfn_get_users", new Object[][] {
			{ BigDecimal.class, id, "p_id" },
		}, Users.class);
		return u;
	}
	
	@Transactional
	public String saveUser(Users user)
	{
		Object id[] = procedure.executeWithType("public.cfn_add_edit_users", new Object[][] {
			{ BigDecimal.class, user.getId(), "p_id" },
			{ String.class, user.getName(), "p_name" },
			{ String.class, user.getEmail(), "p_email" },
			{ String.class, user.getPassword(), "p_password" },
			{ String.class, user.getUserType(), "p_user_type" },
			{ String.class, user.getLoginType(), "p_login_type" },
		});
		return id[0].toString();
	}
}
