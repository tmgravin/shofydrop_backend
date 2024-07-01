package com.shofydrop.service;

import com.shofydrop.dto.UsersDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UsersDto> findAll();

    UsersDto findById(Long id);

    UsersDto update(UsersDto usersDto, Long id);

    UsersDto save(UsersDto usersDto);

    void delete(Long id);
}
