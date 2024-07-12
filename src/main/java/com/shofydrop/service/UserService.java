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

    //Service for sending email verification code
    void sendVerificationEmail(String email);

    // Service for verifying email with code
    void verifyEmailCode(int verificationCode);

    //Service for login user after verifying email
    Users loginUser(String email, String password);

    //Service for sending password reset code
    void forgetPassword(String email);

    //Service for verifying password reset code
    void verifyPasswordResetCode(int verificationCode);

    //Service for resetting password
    void resetPassword(String newPassword, String confirmPassword);
}