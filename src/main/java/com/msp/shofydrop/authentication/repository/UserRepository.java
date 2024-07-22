package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.Users;

import java.util.List;

public interface UserRepository {
    List<Users> getUsers(Long id);

    String saveUser(Users user);
}
