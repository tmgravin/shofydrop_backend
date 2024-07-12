package com.shofydrop.service;

import com.shofydrop.entity.UsersContact;

import java.util.List;

public interface UserContactService {
    UsersContact save(UsersContact usersContact);

    List<UsersContact> findAll();

    UsersContact update(Long id, UsersContact usersContact);

    void findById(Long id);
}