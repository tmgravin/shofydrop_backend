package com.shofydrop.controller;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.dto.UsersDto;
import com.shofydrop.entity.Users;
import com.shofydrop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/api")
public class UserController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> findAllUsers() {
     try{
         ResponseDto responseDto = new ResponseDto();
         responseDto.setStatus(HttpStatus.OK);
         responseDto.setMessage("successfully users details fetched");
         responseDto.setData(userService.findAll());
         return ResponseEntity.status(HttpStatus.OK).body(responseDto);
     }
     catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
     }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(UsersDto usersDto) {
        try {
            Users users = modelMapper.map(usersDto, Users.class);
            usersDto.getName();
            ResponseDto responseDto = new ResponseDto();
            responseDto.setStatus(HttpStatus.OK);
            responseDto.setMessage("successfully user created");
            responseDto.setData(userService.save(users));
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update/:id")
    public ResponseEntity<?> updateUser(@RequestBody UsersDto usersDto, @PathVariable Long id) {
        try {
            Users users = modelMapper.map(usersDto, Users.class);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setStatus(HttpStatus.OK);
            responseDto.setMessage("successfully user updated");
            responseDto.setData(userService.update(users, id));
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/:id")
    public ResponseEntity<?> deleteUser(@RequestBody UsersDto usersDto, @PathVariable Long id) {
        try {
            Users users = modelMapper.map(usersDto, Users.class);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setStatus(HttpStatus.OK);
            responseDto.setMessage("successfully user deleted");
            responseDto.setData(userService.delete(id));
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

}
