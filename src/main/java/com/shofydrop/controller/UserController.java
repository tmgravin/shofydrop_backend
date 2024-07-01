package com.shofydrop.controller;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.dto.UsersDto;
import com.shofydrop.entity.Users;
import com.shofydrop.mapper.UserMapper;
import com.shofydrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody UsersDto usersDto) {
        Users user= UserMapper.maptoUsers(usersDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);

        responseDto.setMessage("User saved successfully");
        responseDto.setData(usersDto);
        userService.save(usersDto);
        System.out.println(responseDto);
        return ResponseEntity.status(200).body(usersDto);

        responseDto.setMessage("successfully users details fetched");
        responseDto.setData(userService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @PutMapping("/update/:id")
    public ResponseEntity<?> update(@RequestBody UsersDto usersDto, @PathVariable Long id) {
        Users user= UserMapper.maptoUsers(usersDto);
        userService.update(usersDto, id);
        ResponseDto responseDto1 = new ResponseDto();
        responseDto1.setStatus(HttpStatus.OK);
        responseDto1.setMessage("Update user successfully");
        responseDto1.setData(usersDto);
        return ResponseEntity.status(200).body(usersDto);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UsersDto>> getAllUsers(){
        List<UsersDto> usersDto = userService.findAll();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("Get All Users successfully");
        responseDto.setData(usersDto);
        System.out.println(responseDto);
        return ResponseEntity.ok(usersDto);
    }

    @GetMapping("/getUserById/:id")
    public ResponseEntity<?> getUserById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("Get successfully");
        responseDto.setData(userService.findById(id));
        System.out.println(responseDto);
        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/delete/:id")
    public ResponseEntity<?> deleteUser(Long id) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("Delete successfully");
        userService.delete(id);
        System.out.println(responseDto);
        return ResponseEntity.status(200).body(responseDto);
    }
}
