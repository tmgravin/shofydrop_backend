package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.UserDetails;
import com.msp.shofydrop.authentication.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUsers(Users user);

    Optional<UserDetails> findByUsersId(Long userId);
}
