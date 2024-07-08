package com.shofydrop.service.impl;

import com.shofydrop.entity.Users;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.UsersRepository;
import com.shofydrop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(Long id) {
        try{
            return usersRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("user does not exist with this id " + id));
        }catch (Exception e){
            throw new RuntimeException("Internal Server Error" + id + e.getMessage());
        }
    }

    @Override
    public Users update(Users user) {
       try{
      return usersRepository.save(user);
       }catch (Exception e){
           throw new RuntimeException("Internal Server Error" + user + e.getMessage());
       }
    }

    @Override
    public Users save(Users users) {
       try{
           return usersRepository.save(users);
       }catch (Exception e){
           throw new RuntimeException("Internal Server Error" + users + e.getMessage());
       }
    }

    @Override
  public Void delete(Long id) {
      try{
          usersRepository.deleteById(id);
          return null;
      }catch (Exception e){
          throw new RuntimeException("Internal Server Error" + id + e.getMessage());
      }
  }

    @Override
    public Users findByEmail(String email) {
        try{
            return usersRepository.findByEmail(email);
        }
        catch (Exception e){
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
