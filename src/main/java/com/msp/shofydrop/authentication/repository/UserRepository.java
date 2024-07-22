package com.msp.shofydrop.authentication.repository;

import java.util.List;

import com.msp.shofydrop.authentication.entity.Users;

public interface UserRepository
{
	List<Users> getUsers(Integer id);
	Integer saveUser(Users user);
}
