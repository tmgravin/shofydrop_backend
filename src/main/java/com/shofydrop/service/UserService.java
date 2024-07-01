package com.shofydrop.service;

import com.shofydrop.dto.UsersDto;
import com.shofydrop.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<Users> findAll();

    Users findById(Long id);

    Users update(Users users, Long id);

    Users save(Users users);

    void delete(Long id);
}
