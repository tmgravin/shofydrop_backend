package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.UserDetails;

import java.util.Optional;

public interface UserDetailsRepo {
    Optional<UserDetails> findByUserId(Long userId);

    String saveUserDetails(UserDetails userDetails);
}
