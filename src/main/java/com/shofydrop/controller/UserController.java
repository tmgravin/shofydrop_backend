package com.shofydrop.controller;

import com.shofydrop.dto.UsersDto;
import com.shofydrop.entity.Users;
import com.shofydrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllUsers() {
        return null;
    }

}
