package com.shofydrop.service.impl;

import com.shofydrop.dto.UsersDto;
import com.shofydrop.entity.Users;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.mapper.UserMapper;
import com.shofydrop.repository.UserRepository;
import com.shofydrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UsersDto> findAll() {
        List<Users> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDto findById(Long id) {
        Users users = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("user does not exist with this id " + id));
        return UserMapper.mapToUserDto(users);
    }

    @Override
    public UsersDto update(UsersDto usersDto, Long id) {
        boolean isExist = userRepository.existsById(id);
        if (isExist) {
            Users users = userRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("user does not exist with this id " + id));
            users.setName(usersDto.getName());
            users.setEmail(usersDto.getEmail());
            users.setPassword(usersDto.getPassword());
            users.setPhone(usersDto.getPhone());
            users.setAddress(usersDto.getAddress());
            users.setIsVerified(usersDto.getIsVerified());
            users.setRole(usersDto.getRole());
            users.setKycCompleted(usersDto.getKycCompleted());
            users.setCreatedAt(usersDto.getCreatedAt());
            users.setUpdatedAt(usersDto.getUpdatedAt());
            users.setLoginType(usersDto.getLoginType());
            Users updatedUsers = userRepository.save(users);
            return UserMapper.mapToUserDto(updatedUsers);
        }
        return null;
    }

    @Override
    public UsersDto save(UsersDto usersDto) {
        Users user = UserMapper.maptoUsers(usersDto);
        Users saveUsers = userRepository.save(user);
        return UserMapper.mapToUserDto(saveUsers);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
