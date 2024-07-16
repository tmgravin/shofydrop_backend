package com.shofydrop.service.impl;

import com.shofydrop.entity.Users;
import com.shofydrop.entity.UsersContact;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.UserContactRepo;
import com.shofydrop.repository.UsersRepository;
import com.shofydrop.service.UserContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserContactServiceImpl implements UserContactService {
    private static final Logger log = LoggerFactory.getLogger(UserContactServiceImpl.class);

    @Autowired
    private UserContactRepo userContactRepo;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersContact save(Long userId, UsersContact usersContact) {
        Users users = usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("The User Does Not Exist" + userId));

        usersContact.setUsers(users);
        return userContactRepo.save(usersContact);
    }

    @Override
    public List<UsersContact> findAll() {
        return userContactRepo.findAll();
    }

    @Override
    public UsersContact update(Long id, UsersContact usersContact) {
        UsersContact existingUserConatct = userContactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        if (existingUserConatct != null) {
            existingUserConatct.setAddress(usersContact.getAddress());
            existingUserConatct.setAddress(usersContact.getAddress());
            existingUserConatct.setCity(usersContact.getCity());
            existingUserConatct.setLatitude(usersContact.getLatitude());
            existingUserConatct.setLongitude(usersContact.getLongitude());
            existingUserConatct.setState(usersContact.getState());
            existingUserConatct.setPhone(usersContact.getPhone());
            existingUserConatct.setUpdatedAt(Timestamp.from(Instant.now()));

        }
        return userContactRepo.save(existingUserConatct);
    }

    @Override
    public Optional<UsersContact> findById(Long id) {
        return userContactRepo.findById(id);
    }

    @Override
    public void delete(Long id) {
        try {
            log.info("User Contact Successfully Deleted");
        } catch (ResourceNotFoundException e) {
            log.error("Not Found" + id);
            throw new ResourceNotFoundException("Not Found" + e);
        } catch (RuntimeException e) {
            log.error("Internal Server Error" + e);
            throw new RuntimeException("Internal Server Error" + e);
        }

    }
}
