package com.shofydrop.controller;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.entity.Users;
import com.shofydrop.repository.UsersRepository;
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

    private UsersRepository usersRepository;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

   @GetMapping("/getAllUsers")
   public List<Users> findAllUsers() {
       log.info("Getting All Users");
       return userService.findAll();
   }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Long id) {
        Users user = userService.findById(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Get User By Id");
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setData(user);
        log.info("Getting User: " + user.toString());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody Users users) {
       log.info("Saving User: " + users.toString());
       return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(users));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        log.info("Deleting User: " + id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody Users users) {
        log.info("Updating User: " + users.toString());
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(users));
    }
}
