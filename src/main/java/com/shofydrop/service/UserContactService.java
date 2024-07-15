package com.shofydrop.service;

import com.shofydrop.entity.UsersContact;

import java.util.List;
import java.util.Optional;

public interface UserContactService {
    UsersContact save(Long userId, UsersContact usersContact);

    List<UsersContact> findAll();

    UsersContact update(Long id, UsersContact usersContact);

    Optional<UsersContact> findById(Long id);


    void delete(Long id);
}