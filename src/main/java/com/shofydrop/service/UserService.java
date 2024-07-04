package com.shofydrop.service;

import com.shofydrop.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<Users> findAll();
    Users findById(Long id);
    Users update(Users users, Long id);
    Users save(Users users);
<<<<<<< HEAD

    Object delete(Long id);
=======
    void delete(Long id);
>>>>>>> c24224ba947faebef11b2095a462323b7d332f1e
}
