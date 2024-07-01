package com.shofydrop.controller;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.dto.UsersDto;
import com.shofydrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("successfully users details fetched");
        List<UsersDto> usersDto = userService.findAll();
        responseDto.setData(usersDto);
        return ResponseEntity.ok(responseDto);
    }

}
