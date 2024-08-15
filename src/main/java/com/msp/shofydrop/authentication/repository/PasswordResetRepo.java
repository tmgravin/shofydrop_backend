package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.PasswordResetCode;

import java.util.List;
import java.util.Optional;

public interface PasswordResetRepo {
    String saveCode(PasswordResetCode code);

    Optional<PasswordResetCode> findByCode(int code);

    Optional<PasswordResetCode> findByIsVerified(String isVerified);

    List<PasswordResetCode> findAllCodesByUserId(Long userID);

    void deletePasswordResetCode(Long userId);
}
