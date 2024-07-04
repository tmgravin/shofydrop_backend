package com.shofydrop.service.impl;

import com.shofydrop.entity.Users;
import com.shofydrop.exception.ResourceNotFoundException;

import com.shofydrop.repository.UsersRepository;

import com.shofydrop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(Long id) {
        return usersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("user does not exist with this id " + id));
    }

    @Override
    public Users update(Users user, Long id) {
        boolean isExist = usersRepository.existsById(id);

        if (isExist) {
            Users existingUser = usersRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("user does not exist with this id " + id));
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhone(user.getPhone());
            existingUser.setAddress(user.getAddress());
            existingUser.setIsVerified(user.getIsVerified());
            existingUser.setRole(user.getRole());
            existingUser.setKycCompleted(user.getKycCompleted());
            existingUser.setCreatedAt(user.getCreatedAt());
            existingUser.setUpdatedAt(user.getUpdatedAt());
            existingUser.setLoginType(user.getLoginType());
            return usersRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public Users save(Users users) {
       return usersRepository.save(users);
    }
    @Override
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }
}
