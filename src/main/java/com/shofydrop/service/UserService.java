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

    //Service for user signup
    Users signupUser(Users users);

    //Service for verify user after signup
    void sendVerificationEmail(String email);

    void verifyUserEmail(int verificationCode);

    //Service for user login
    Users loginUser(String email, String password);

    //Service for forget password, verifyUser and resetPassword
    void forgetPassword(String email);

    void verifyCode(int verificationCode);

    void resetPassword(String newPassword, String confirmPassword);
}
