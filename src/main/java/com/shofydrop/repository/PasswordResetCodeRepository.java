package com.shofydrop.repository;

import com.shofydrop.entity.PasswordResetCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, Long> {
    Optional<PasswordResetCode> findByCode(int code);

    Optional<PasswordResetCode> findByVerified(boolean verified);

    void deleteByCode(int code);

}
