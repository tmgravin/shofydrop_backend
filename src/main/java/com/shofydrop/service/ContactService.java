package com.shofydrop.service;

import com.shofydrop.entity.UsersContact;

import java.util.List;


public interface ContactService {
    List<UsersContact> findAll();

    UsersContact findById(Long id);

    UsersContact save(UsersContact usersContact);

    UsersContact update(UsersContact usersContact);

    void delete(Long id);

}
