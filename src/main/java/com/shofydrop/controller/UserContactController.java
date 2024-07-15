package com.shofydrop.controller;

import com.shofydrop.entity.UsersContact;
import com.shofydrop.service.UserContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/contact/api")
public class UserContactController {
    @Autowired
    private UserContactService userContactService;

    private static final Logger log = LoggerFactory.getLogger(UserContactController.class);

    @PostMapping("/addContact/{id}")
    public ResponseEntity<UsersContact> addUserContact(@PathVariable Long userId, UsersContact usersContact) {
        UsersContact usersContact1 = userContactService.save(userId, usersContact);
        log.info("User Successfully Saved" + usersContact1);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersContact1);
    }

    @GetMapping("/getAll")
    public List<UsersContact> getAllUserContact() {
        log.info("Getting All Users Contact");
        return userContactService.findAll();
    }

    @GetMapping("/getUserContact/{id}")
    public ResponseEntity<Optional<UsersContact>> getUserContact(@PathVariable Long id) {
        Optional<UsersContact> usersContact = userContactService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(usersContact);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserConatct(@PathVariable Long id, UsersContact usersContact) {
        userContactService.update(id, usersContact);
        log.info("User Contact Updated Succefully:", id);
        return ResponseEntity.status(HttpStatus.OK).body("User Contact Updated Succefully" + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserContacr(@PathVariable Long id) {
        userContactService.delete(id);
        log.info("User Contact Delete Successfully:", id);
        return ResponseEntity.status(HttpStatus.OK).body("User Contact Delete Successfully:" + id);
    }
}