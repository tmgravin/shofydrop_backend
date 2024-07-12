package com.shofydrop.controller;

import com.shofydrop.entity.Users;
import com.shofydrop.exception.ResourceNotFoundException;
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

    //Api for user signup
    @PostMapping("/signup")
    public ResponseEntity<Users> signupUser(@RequestBody Users user) {
        try {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            Users registerUser = userService.signupUser(user);
            return ResponseEntity.ok(registerUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Api for verifying email code
    @PostMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmailCode(@RequestParam int verificationCode) {
        try {
            userService.verifyEmailCode(verificationCode);
            return ResponseEntity.ok("User Verified Successfully!");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid verification code.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification process failed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }

    //Api for login user after verifying email
    @PostMapping("/login")
    public ResponseEntity<Users> loginUser(@RequestParam String email, @RequestParam String password) {
        Users loginUSer = userService.loginUser(email, password);
        return ResponseEntity.ok(loginUSer);
    }

    //Api for sending password reset code
    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestParam String email) {
        try {
            userService.forgetPassword(email);
            return ResponseEntity.ok("Password verification code is send to your email.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist with this email.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }

    //Api for verifying password reset code
    @PostMapping("/verifyResetPasswordCode")
    public ResponseEntity<String> verifyResetPasswordCode(@RequestParam int verificationCode) {
        try {
            userService.verifyPasswordResetCode(verificationCode);
            return ResponseEntity.ok("Verification code verified successfully!");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid verification code.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification process failed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }

    //Api for resetting password
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestParam String newPassword, @RequestParam String confirmPassword) {
        try {
            userService.resetPassword(newPassword, confirmPassword);
            return ResponseEntity.ok("Password reset successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }

}
