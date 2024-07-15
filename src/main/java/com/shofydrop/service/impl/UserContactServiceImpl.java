package com.shofydrop.service.impl;

import com.shofydrop.entity.UsersContact;
import com.shofydrop.repository.UserContactRepo;
import com.shofydrop.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserContactServiceImpl implements ContactService {
    @Autowired
    private UserContactRepo userContactRepo;

    @Override
    public List<UsersContact> findAll() {
        return userContactRepo.findAll();
    }

    @Override
    public UsersContact findById(Long id) {
        return userContactRepo.findById(id).orElseThrow(() -> new
                RuntimeException("Contact does not exist with id" + id));
    }


    @Override
    public UsersContact save(UsersContact usersContact) {
        return userContactRepo.save(usersContact);
    }


    @Override

    public UsersContact update(UsersContact usersContact) {
        boolean isExist = userContactRepo.existsById(usersContact.getId());
        if (isExist) {
            UsersContact isExistingUsersContact = userContactRepo.findById(usersContact.getId()).get();
            isExistingUsersContact.setPhone(usersContact.getPhone());
            isExistingUsersContact.setEmail(usersContact.getEmail());
            isExistingUsersContact.setAddress(usersContact.getAddress());
            isExistingUsersContact.setCity(usersContact.getCity());
            isExistingUsersContact.setState(usersContact.getState());
            isExistingUsersContact.setCreatedAt(usersContact.getCreatedAt());
            isExistingUsersContact.setUpdatedAt(usersContact.getUpdatedAt());
            return userContactRepo.save(isExistingUsersContact);
        }
        return null;
    }


    @Override
    public void delete(Long id) {
        userContactRepo.deleteById(id);
    }
}
