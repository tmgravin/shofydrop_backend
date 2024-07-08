package com.shofydrop.service;

import com.shofydrop.entity.UsersContact;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ContactService {
    List<UsersContact> findAll();
    UsersContact findById(Long id);
    UsersContact save(UsersContact usersContact);
    UsersContact update(UsersContact usersContact);
    void delete(Long id);

}
