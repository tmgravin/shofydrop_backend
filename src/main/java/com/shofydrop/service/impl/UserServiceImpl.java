package com.shofydrop.service.impl;

import com.shofydrop.entity.Users;
import com.shofydrop.exception.ResourceNotFoundException;
<<<<<<< HEAD
import com.shofydrop.repository.UsersRepository;
=======
import com.shofydrop.repository.UserRepository;
>>>>>>> 6c10720 (all changes from start)
import com.shofydrop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
<<<<<<< HEAD
    private UsersRepository usersRepository;

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
=======
    private UserRepository userRepository;

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
>>>>>>> 6c10720 (all changes from start)
    }

    @Override
    public Users findById(Long id) {
<<<<<<< HEAD
        return usersRepository.findById(id).orElseThrow(() ->
=======
        return userRepository.findById(id).orElseThrow(() ->
>>>>>>> 6c10720 (all changes from start)
                new ResourceNotFoundException("user does not exist with this id " + id));
    }

    @Override
    public Users update(Users user, Long id) {
<<<<<<< HEAD
        boolean isExist = usersRepository.existsById(id);
        if (isExist) {
            Users existingUser = usersRepository.findById(id).orElseThrow(() ->
=======
        boolean isExist = userRepository.existsById(id);
        if (isExist) {
            Users existingUser = userRepository.findById(id).orElseThrow(() ->
>>>>>>> 6c10720 (all changes from start)
                    new ResourceNotFoundException("user does not exist with this id " + id));
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhone(user.getPhone());
            existingUser.setAddress(user.getAddress());
            existingUser.setIsVerified(user.getIsVerified());
            existingUser.setRole(user.getRole());
            existingUser.setKycCompleted(user.getKycCompleted());
            existingUser.setCreatedAt(user.getCreatedAt());
            existingUser.setUpdatedAt(user.getUpdatedAt());
            existingUser.setLoginType(user.getLoginType());
<<<<<<< HEAD
            return usersRepository.save(existingUser);
=======
            return userRepository.save(existingUser);
>>>>>>> 6c10720 (all changes from start)
        }
        return null;
    }

    @Override
    public Users save(Users users) {
<<<<<<< HEAD
       return usersRepository.save(users);
=======
       return userRepository.save(users);
>>>>>>> 6c10720 (all changes from start)
    }

    @Override
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }
}
