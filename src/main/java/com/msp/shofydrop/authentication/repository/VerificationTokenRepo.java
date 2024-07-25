package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.EmailVerificationToken;

import java.util.Optional;

public interface VerificationTokenRepo {
    String saveToken(EmailVerificationToken token);

    Optional<EmailVerificationToken> findByToken(String token);
}
