package com.shofydrop.service.impl;

import com.shofydrop.entity.UsersContact;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.UserContactRepo;
import com.shofydrop.service.UserContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class UserContactServiceImpl implements UserContactService {
    private static final Logger log = LoggerFactory.getLogger(UserContactServiceImpl.class);
    @Autowired
    private UserContactRepo userContactRepo;


    @Override
    public UsersContact save(UsersContact usersContact) {
        return userContactRepo.save(usersContact);
    }

    @Override
    public List<UsersContact> findAll() {
        return userContactRepo.findAll();
    }

    @Override
    public UsersContact update(Long id, UsersContact usersContact) {
        try {
            UsersContact existingUserConatct = userContactRepo.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("User Contact Does Not Exist" + id));
            existingUserConatct.setAddress(usersContact.getAddress());
            existingUserConatct.setAddress(usersContact.getAddress());
            existingUserConatct.setCity(usersContact.getCity());
            existingUserConatct.setLatitude(usersContact.getLatitude());
            existingUserConatct.setLongitude(usersContact.getLongitude());
            existingUserConatct.setState(usersContact.getState());
            existingUserConatct.setPhone(usersContact.getPhone());
            existingUserConatct.setUpdatedAt(Timestamp.from(Instant.now()));
            log.info("User Sontact Successfully Updated" + id);
            return userContactRepo.save(existingUserConatct);
        } catch (RuntimeException e) {
            log.error("Internal Server Error", e);
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error");
        }
    }

    @Override
    public UsersContact findById(Long id) {
        return userContactRepo.findByUserId(id);
    }

    @Override
    public void delete(Long id) {
        log.info("User Contact Successfully Deleted");
    }

    @Override
    public UsersContact findByUserId(Long userId) {
        return null;
    }
}
