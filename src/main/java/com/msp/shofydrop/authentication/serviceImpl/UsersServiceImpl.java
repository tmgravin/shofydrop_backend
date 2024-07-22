package com.msp.shofydrop.authentication.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.repository.UserRepository;
import com.msp.shofydrop.authentication.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public List<Users> get(Integer id)
	{
		return userRepository.getUsers(id);
	}
	
	@Override
	@Transactional
	public Integer saveUsers(Users user)
	{
		return userRepository.saveUser(user);
	}
}
