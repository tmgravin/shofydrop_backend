package com.msp.shofydrop.authentication.service;

import com.msp.shofydrop.authentication.entity.Users;

import java.util.List;

public interface UsersService {
    List<Users> get(Long id);

    String saveUsers(Users user);
}
