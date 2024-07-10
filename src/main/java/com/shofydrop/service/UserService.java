package com.shofydrop.service;

import com.shofydrop.entity.Users;

import java.util.List;


public interface UserService {
    List<Users> findAll();

    Users findById(Long id);

    Users update(Long id, Users users);

    Void delete(Long id);

    Users findByEmail(String email);

    Users findByName(String name);

    //For User login and signup
    Users signupUser(Users users);

    Users loginUser(String email, String password);

    //forget password, verifyUser and resetPassword
    void forgetPassword(String email);

    void verifyCode(int verificationCode);

    void resetPassword(String newPassword, String confirmPassword);
}
