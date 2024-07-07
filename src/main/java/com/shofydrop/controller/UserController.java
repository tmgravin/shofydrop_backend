package com.shofydrop.controller;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.entity.Users;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.UsersRepository;
import com.shofydrop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private UsersRepository usersRepository;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> findAllUsers() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("successfully users details fetched");
        responseDto.setData(userService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long id) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("successfully user details fetched");
        responseDto.setData(userService.findById(id));
        logger.info("successfully user details fetched : " + id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users users) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("successfully user registered");
        responseDto.setData(userService.save(users));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("successfully user deleted");
        responseDto.setData(userService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody Users users) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setStatus(HttpStatus.OK);
            responseDto.setMessage("successfully user updated");
            responseDto.setData(userService.update(users));
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
       }
}
