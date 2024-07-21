package com.msp.shofydrop.authentication.controller;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.service.UserService;
import com.msp.shofydrop.exception.EmailNotVerifiedException;
import com.msp.shofydrop.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/users/api")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public List<Users> findAllUsers(@RequestParam(name = "id", required = false) Integer Id) {
        log.info("Inside findAllUsers method of UserController (authentication package)");
        return userService.getUsers(Id);
    }

//    @GetMapping("/getUser/{id}")
//    public ResponseEntity<Users> getUserById(@PathVariable("id") Long id) {
//        log.info("Inside getUserById method of UserController (authentication package)");
//        Users user = userService.findById(id);
//        log.info("Getting User: {}", user.toString());
//        return ResponseEntity.status(HttpStatus.OK).body(user);
//    }
//
//    @DeleteMapping("/deleteUser/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//        log.info("Inside deleteUser method of UserController (authentication package)");
//        userService.delete(id);
//        log.info("Deleting User: {}", id);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    @PutMapping("/updateUser/{id}")
//    public String updateUser(@PathVariable Long id, @RequestBody Users users) {
//        log.info("Inside updateUser method of UserController (authentication package)");
//        log.info("Updating User: {}", users.toString());
//        userService.update(id, users);
//        return "User updated successfully";
//    }
//
//    //Api for user signup
//    @PostMapping("/signup")
//    public ResponseEntity<Users> signupUser(@RequestBody Users user) {
//        log.info("Inside signupUser method of UserController (authentication package)");
//        try {
//            if (user.getPassword() == null || user.getPassword().isEmpty()) {
//                return ResponseEntity.badRequest().body(null);
//            }
//            Users registerUser = userService.signupUser(user);
//            return ResponseEntity.ok(registerUser);
//        } catch (IllegalArgumentException e) {
//            log.error("Error during user signup: {}", e.getMessage());
//            return ResponseEntity.badRequest().body(null);
//        } catch (Exception e) {
//            log.error("Unexpected error during user signup: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    //Api for verifying email token
//    @GetMapping("/verifyEmail")
//    public ModelAndView verifyEmailCode(@RequestParam("token") String verificationToken) {
//        log.info("Inside verifyEmailCode method of UserController (authentication package)");
//        ModelAndView modelAndView = new ModelAndView();
//        try {
//            userService.verifyEmailToken(verificationToken);
//            modelAndView.setViewName("verifySuccess");
//        } catch (IllegalStateException e) {
//            log.error("Error verifying email token: {}", e.getMessage());
//            modelAndView.addObject("error", e.getMessage());
//            modelAndView.setViewName("error");
//        } catch (ResourceNotFoundException e) {
//            log.error("Email verification failed: {}", e.getMessage());
//            modelAndView.addObject("error", "User doesn't exist with this email.");
//            modelAndView.setViewName("error");
//        } catch (IllegalArgumentException e) {
//            log.error("Verification process failed: {}", e.getMessage());
//            modelAndView.addObject("error", "Verification process failed.");
//            modelAndView.setViewName("error");
//        } catch (Exception e) {
//            log.error("Internal server error during email verification: {}", e.getMessage());
//            modelAndView.addObject("error", "Internal server error.");
//            modelAndView.setViewName("error");
//        }
//        return modelAndView;
//    }
//
//    //Api for login user after verifying email
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
//        log.info("Inside loginUser method of UserController (authentication package)");
//        try {
//            Users loginUser = userService.loginUser(email, password);
//            return ResponseEntity.status(HttpStatus.OK).body("User login successfully.");
//        } catch (ResourceNotFoundException e) {
//            log.error("Login failed: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (IllegalStateException e) {
//            log.error("Login failed due to bad request: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception e) {
//            log.error("Unexpected error during login: {}", e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    //Api for sending password reset code
//    @PostMapping("/forgetPassword")
//    public ResponseEntity<String> forgetPassword(@RequestParam String email) {
//        log.info("Inside forgetPassword method of UserController (authentication package)");
//        try {
//            userService.forgetPassword(email);
//            return ResponseEntity.status(HttpStatus.OK).body("Password verification code sent to your email.");
//        } catch (ResourceNotFoundException e) {
//            log.error("Password reset code request failed: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist with this email.");
//        } catch (EmailNotVerifiedException e) {
//            log.error("Email not verified: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception e) {
//            log.error("Unexpected error during password reset code request: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
//        }
//    }
//
//    //Api for verifying password reset code
//    @PostMapping("/verifyResetPasswordCode")
//    public ResponseEntity<String> verifyResetPasswordCode(@RequestParam int verificationCode) {
//        log.info("Inside verifyResetPasswordCode method of UserController (authentication package)");
//        try {
//            userService.verifyPasswordResetCode(verificationCode);
//            return ResponseEntity.ok("Verification code verified successfully!");
//        } catch (ResourceNotFoundException e) {
//            log.error("Invalid verification code: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid verification code.");
//        } catch (IllegalStateException e) {
//            log.error("Verification process failed: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification process failed.");
//        } catch (Exception e) {
//            log.error("Unexpected error during verification of password reset code: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
//        }
//    }
//
//    //Api for resetting password
//    @PostMapping("/resetPassword")
//    public ResponseEntity<String> resetPassword(@RequestParam String newPassword, @RequestParam String confirmPassword) {
//        log.info("Inside resetPassword method of UserController (authentication package)");
//        try {
//            userService.resetPassword(newPassword, confirmPassword);
//            return ResponseEntity.ok("Password reset successfully!");
//        } catch (IllegalArgumentException e) {
//            log.error("Password reset failed: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception e) {
//            log.error("Unexpected error during password reset: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
//        }
//    }
}
