package com.shofydrop.service.impl;

import com.shofydrop.entity.Users;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.UsersRepository;
import com.shofydrop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(Long id) {
        try {
            return usersRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("user does not exist with this id " + id));
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + id + e.getMessage());
        }
    }

    @Override
    public Users update(Long id, Users users) {
        try {
            Users existingUser = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            existingUser.setName(users.getName());
            existingUser.setEmail(users.getEmail());
            existingUser.setKycCompleted(users.getKycCompleted());
            existingUser.setIsVerified(users.getIsVerified());
            existingUser.setUserType(users.getUserType());
            existingUser.setLoginType(users.getLoginType());
            existingUser.setUpdatedAt(Timestamp.from(Instant.now()));
            log.info("User Successfully Updated");
            return usersRepository.save(existingUser);
        } catch (Exception e) {
            log.error("Error updating user", e);
            throw new RuntimeException("Internal Server Error" + e.getMessage());
        }
    }


    @Override
    public Users save(Users users) {
        try {
            return usersRepository.save(users);
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + users + e.getMessage());
        }
    }

    @Override
    public Void delete(Long id) {
        try {
            usersRepository.deleteById(id);
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + id + e.getMessage());
        }
    }

    @Override
    public Users findByEmail(String email) {
        try {
            return usersRepository.findByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + email + e.getMessage());
        }
    }

    @Override
    public Users findByName(String name) {
        try {
            return usersRepository.findByName(name);
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + name + e.getMessage());
        }
    }

}
