package com.shofydrop.service;

import com.shofydrop.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<Users> findAll();
    Users findById(Long id);
    Users update(Users users);
    Users save(Users users);
    Void delete(Long id);
    Users findByEmail(String email);
    Users findByName(String name);
}
