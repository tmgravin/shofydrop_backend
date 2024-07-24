package com.msp.shofydrop.authentication.serviceImpl;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.repository.UserRepository;
import com.msp.shofydrop.authentication.service.UsersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<Users> get(Long id) {
        return userRepository.getUsers(id);
    }

    @Override
    @Transactional
    public String saveUsers(Users user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return userRepository.saveUser(user);
    }
}
