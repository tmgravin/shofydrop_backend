package com.msp.shofydrop.authentication.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/contact/api")
public class UserContactController {
//    @Autowired
//    private UserContactService userContactService;
//
//    private static final Logger log = LoggerFactory.getLogger(UserContactController.class);
//
//    @PostMapping("/addContact/{userId}")
//    public UsersContact addUserContact(@PathVariable Long userId, @RequestBody UsersContact usersContact) {
//        return userContactService.save(userId, usersContact);
//    }
//
//    @GetMapping("/getAll")
//    public List<UsersContact> getAllUserContact() {
//        log.info("Getting All Users Contact");
//        return userContactService.findAll();
//    }
//
//    @GetMapping("/getUserContact/{id}")
//    public ResponseEntity<Optional<UsersContact>> getUserContact(@PathVariable Long id) {
//        Optional<UsersContact> usersContact = userContactService.findById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(usersContact);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateUserConatct(@PathVariable Long id, @RequestBody UsersContact usersContact) {
//        userContactService.update(id, usersContact);
//        log.info("User Contact Updated Succefully:", id);
//        return ResponseEntity.status(HttpStatus.OK).body("User Contact Updated Succefully");
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteUserContacr(@PathVariable Long id) {
//        userContactService.delete(id);
//        log.info("User Contact Delete Successfully:", id);
//        return ResponseEntity.status(HttpStatus.OK).body("User Contact Delete Successfully:" + id);
//    }
}