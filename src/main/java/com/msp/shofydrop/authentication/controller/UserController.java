package com.msp.shofydrop.authentication.controller;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.service.UsersService;
import com.msp.shofydrop.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
@RequestMapping("/api/auth/users")
public class UserController {
    @Autowired
    private UsersService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    //Api for get users by id and get all users
    @GetMapping("/")
    public ResponseEntity<?> ListAllUsers(@RequestParam(name = "id", required = false) Long id) {
        log.info("Inside getUsers method of UserController (authentication)");
        try {
            return ResponseEntity.ok().body(userService.getAllUsers(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    //Api for user signup
    @PostMapping("/")
    public ResponseEntity<?> signupUser(@RequestBody Users user) {
        log.info("Inside signupUser method of UserController (authentication)");
        try {
            return ResponseEntity.ok().body(userService.signupUser(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    //Api for user login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password){
        log.info("Inside loginUser method of UserController (authentication)");
        try {
            Users loginUser = userService.loginUser(email, password);
            log.info("User login successfully.");
            return ResponseEntity.status(HttpStatus.OK).body("User login successfully.");
        } catch (ResourceNotFoundException e) {
            log.error("Resource not found: ", e);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (RuntimeException e){
            log.error("Runtime exception: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Api for verifying email token
    @GetMapping("/verifyEmail")
    public ModelAndView verifyEmailToken(@RequestParam("token") String verificationToken){
        log.info("Inside verifyEmailToken method of UserController (authentication)");
        ModelAndView modelAndView = new ModelAndView();
        try{
            userService.verifyEmailToken(verificationToken);
            modelAndView.setViewName("verifySuccess");
        }catch (IllegalStateException e){
            log.error("Verification token expired.", e);
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("error");
        }catch (ResourceNotFoundException e){
            log.error("User doesn't exist with this email.", e);
            modelAndView.addObject("error", "User doesn't exist with this email.");
            modelAndView.setViewName("error");
        }catch (IllegalArgumentException e){
            log.error("Verification process failed.", e);
            modelAndView.addObject("error", "Verification process failed.");
            modelAndView.setViewName("error");
        }catch (Exception e){
            log.error("Internal server error.", e);
            modelAndView.addObject("error", "Internal server error.");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }


}
