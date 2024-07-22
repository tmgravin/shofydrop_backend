package com.msp.shofydrop.authentication.controller;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UsersService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public ResponseEntity<?> getUsers(@RequestParam(name = "id", required = false) Long id) {
        log.info("Inside getUsers method of UserController (authentication)");
        try {
            return ResponseEntity.ok().body(userService.get(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> save(@RequestBody Users user) {
        log.info("Inside save method of UserController (authentication)");
        try {
            return ResponseEntity.ok().body(userService.saveUsers(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }
}
