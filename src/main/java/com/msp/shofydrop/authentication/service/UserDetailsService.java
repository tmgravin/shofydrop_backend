package com.msp.shofydrop.authentication.service;

import com.msp.shofydrop.authentication.entity.UserDetails;

import java.util.List;

public interface UserDetailsService {
    List<UserDetails> getUserDetails(Long userId);

    String seveUserDetails(UserDetails userDetails);
}