package com.shofydrop.repository;

import com.shofydrop.entity.UserDetails;
import com.shofydrop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUsers(Users users);
}
