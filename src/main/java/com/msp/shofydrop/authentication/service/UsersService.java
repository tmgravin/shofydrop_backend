package com.msp.shofydrop.authentication.service;

import com.msp.shofydrop.authentication.entity.Users;
import java.util.Optional;

public interface UsersService {
    //Service for get users by id and get all users
    Optional<Users> getAllUsers(Long id);

    //Service for user signup
    String signupUser(Users users);

    //Service for user login
    Users loginUser(String email, String password);

    //Service for sending email verification token
    void sendVerificationEmail(String email);

    //Service for verifying email with token
    void verifyEmailToken(String token);

    //Service for sending password reset code
    void forgetPassword(String email);

    //Service for verifying password reset code
    void verifyPasswordResetCode(int verificationCode);

    //Service for Resetting password
    void resetPassword(String newPassword, String confirmPassword);
}
