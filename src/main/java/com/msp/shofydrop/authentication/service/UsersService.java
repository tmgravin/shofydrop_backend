package com.msp.shofydrop.authentication.service;

import com.msp.shofydrop.authentication.entity.Users;

import java.util.List;

public interface UsersService {
    List<Users> get(Long id);

    //Service for user signup
    String signupUser(Users users);

    //Service for user login
    Users loginUser(String email, String password);
}
