package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.UserDetails;

import java.util.List;

public interface UserDetailsRepo {
    List<UserDetails> get(Long UserId);

    String saveUserDetails(UserDetails userDetails);
}
