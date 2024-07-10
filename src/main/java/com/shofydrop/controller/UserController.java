package com.shofydrop.controller;

import com.shofydrop.entity.Users;
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

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getAllUsers")
    public List<Users> findAllUsers() {
        log.info("Getting All Users");
        return userService.findAll();
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Long id) {
        Users user = userService.findById(id);
        log.info("Getting User: {}", user.toString());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        log.info("Deleting User: {}", id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody Users users) {
        log.info("Updating User: {}", users.toString());
        userService.update(id, users);
        return "User updated successfully";
    }

    //Controller for logging and signing user
    @PostMapping("/signup")
    public ResponseEntity<Users> signupUser(@RequestBody Users user){
        if (user.getPassword() == null || user.getPassword().isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }
        Users registerUser = userService.signupUser(user);
        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Users> loginUser(@RequestParam String email, @RequestParam String password){
        Users loginUSer = userService.loginUser(email,password);
        return ResponseEntity.ok(loginUSer);
    }

    //Forget Password, Verification Code and reset password
    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestParam String email){
        userService.forgetPassword(email);
        return ResponseEntity.ok("Verification code is send to your email.");
    }

    @PostMapping("/verifyCode")
    public ResponseEntity<String> verifyUser(@RequestParam int verificationCode){
        userService.verifyCode(verificationCode);
        return ResponseEntity.ok("User verified successfully!");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestParam String newPassword, @RequestParam String confirmPassword){
        try{
            userService.resetPassword(newPassword, confirmPassword);
            return ResponseEntity.ok("Password changed successfully!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error resetting password");
        }
    }

}
