package com.msp.shofydrop.authentication.repositoryImpl;

import com.msp.shofydrop.authentication.entity.EmailVerificationToken;
import com.msp.shofydrop.authentication.repository.VerificationTokenRepo;

import java.util.Optional;

public class VerificationTokenRepoImpl implements VerificationTokenRepo {
    @Override
    public Optional<EmailVerificationToken> findByToken(String token) {
        return Optional.empty();
    }
}
