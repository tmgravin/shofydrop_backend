package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<Users> findById(Long id);

    String saveUser(Users user);

    Optional<Users> findByEmail(String email);
}