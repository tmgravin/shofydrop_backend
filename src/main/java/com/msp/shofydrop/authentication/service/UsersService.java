package com.msp.shofydrop.authentication.service;

import java.util.List;

import com.msp.shofydrop.authentication.entity.Users;

public interface UsersService
{
	List<Users> get(Integer id);
	Integer saveUsers(Users user);
}
