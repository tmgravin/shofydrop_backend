package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<Users> getUsers(Long id);

    String saveUser(Users user);

    Optional<Users> findByEmail(String email);
}