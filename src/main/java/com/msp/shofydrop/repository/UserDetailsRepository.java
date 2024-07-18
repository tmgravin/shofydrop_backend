package com.msp.shofydrop.repository;

import com.msp.shofydrop.entity.Users;
import com.msp.shofydrop.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUsers(Users users);

    Optional<UserDetails> findByUsersId(Long userId);

}
