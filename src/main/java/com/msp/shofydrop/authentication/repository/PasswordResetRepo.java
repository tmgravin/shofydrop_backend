package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.PasswordResetCode;

import java.util.Optional;

public interface PasswordResetRepo {
    String saveCode(PasswordResetCode code);

    Optional<PasswordResetCode> findByCode(int code);

}
