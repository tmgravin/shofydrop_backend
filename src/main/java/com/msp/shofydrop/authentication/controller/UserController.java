package com.msp.shofydrop.authentication.controller;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.service.UsersService;
import com.msp.shofydrop.exception.EmailRelatedException;
import com.msp.shofydrop.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/api/auth/users")
public class UserController {
    private static final long EMAIL_EXPIRATION_TIME = 3600000;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UsersService userService;

    //Api for get users by id and get all users
    @GetMapping("/")
    public ResponseEntity<?> getUsers(@RequestParam(name = "id", required = false) Long id) {
        log.info("Inside getUsers method of UserController (authentication)");
        try {
            Object result = userService.getAllUsers(id);
            return ResponseEntity.ok(result);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Api for user signup and sending email verification email
    @PostMapping("/")
    public ResponseEntity<String> signupUser(@RequestBody Users user, HttpSession session) {
        log.info("Inside signupUser method of UserController (authentication)");
        try {
            String registerUser = userService.signupUser(user);

            //Store the email and current timestamp in the session
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("emailStoredTime", System.currentTimeMillis());

            return ResponseEntity.ok().body("User signup successfully. Please verify your email for login.");
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Exception: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Api for requesting email verification again
    @PostMapping("/resendVerificationEmail")
    public ResponseEntity<String> resendVerificationEmail(@RequestParam(required = false) String email, HttpSession session) {
        log.info("Inside resendVerificationEmail method of UserController (authentication)");
        try {
            String sessionEmail = (String) session.getAttribute("userEmail");
            Long emailStoredTime = (Long) session.getAttribute("emailStoredTime");
            long currentTime = System.currentTimeMillis();
            if (sessionEmail != null && emailStoredTime != null && (currentTime - emailStoredTime) <= EMAIL_EXPIRATION_TIME) {
                email = sessionEmail;
            } else if (email == null || email.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Session expired. Please provide your email again.");
            }
            userService.sendVerificationEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body("Email verification token is send again successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Api for user login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        log.info("Inside loginUser method of UserController (authentication)");
        try {
            Users loginUser = userService.loginUser(email, password);
            log.info("User login successfully.");
            return ResponseEntity.status(HttpStatus.OK).body("User login successfully.");
        } catch (ResourceNotFoundException e) {
            log.error("Resource not found: ", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            log.error("Runtime exception: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Api for verifying email token
    @GetMapping("/verifyEmail")
    public ModelAndView verifyEmailToken(@RequestParam("token") String verificationToken) {
        log.info("Inside verifyEmailToken method of UserController (authentication)");
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.verifyEmailToken(verificationToken);
            modelAndView.setViewName("verifySuccess");
        } catch (IllegalStateException e) {
            log.error("Verification token expired.", e);
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("error");
        } catch (ResourceNotFoundException e) {
            log.error("User doesn't exist with this email.", e);
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("error");
        } catch (IllegalArgumentException e) {
            log.error("Verification process failed.", e);
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("error");
        } catch (Exception e) {
            log.error("Internal server error.", e);
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    //Api for forget password and sending reset code
    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestParam String email, HttpSession session) {
        log.info("Inside forgetPassword method of UserController (authentication)");
        try {
            userService.forgetPassword(email);

            session.setAttribute("userEmail", email);
            session.setAttribute("emailStoredTime", System.currentTimeMillis());

            return ResponseEntity.status(HttpStatus.OK).body("Password verification code is send to your email.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EmailRelatedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Api for requesting password reset code again
    @PostMapping("/resendCode")
    public ResponseEntity<String> resendPasswordResetCode(@RequestParam(required = false) String email, HttpSession session) {
        log.info("Inside resendPasswordResetCode method of UserController (authentication");
        try {
            String sessionEmail = (String) session.getAttribute("userEmail");
            Long emailStoredTime = (Long) session.getAttribute("emailStoredTime");
            long currentTime = System.currentTimeMillis();

            if (sessionEmail != null && emailStoredTime != null && (currentTime - emailStoredTime) <= EMAIL_EXPIRATION_TIME) {
                email = sessionEmail;
            } else if (email == null || email.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Session expired. Please provide your email again.");
            }
            userService.forgetPassword(email);
            return ResponseEntity.status(HttpStatus.OK).body("Password verification code is sent again to your email.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EmailRelatedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Api for verifying password reset code
    @PostMapping("/verifyPasswordResetCode")
    public ResponseEntity<String> verifyPasswordResetCode(@RequestParam int verificationCode) {
        log.info("Inside verifyPasswordResetCode method of UserController (authentication)");
        try {
            userService.verifyPasswordResetCode(verificationCode);
            return ResponseEntity.status(HttpStatus.OK).body("Password Reset code verified successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    //Api for resetting password
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestParam String newPassword, @RequestParam String confirmPassword) {
        log.info("Inside resetPassword method of UserController (authentication)");
        try {
            userService.resetPassword(newPassword, confirmPassword);
            return ResponseEntity.status(HttpStatus.OK).body("Password reset successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Api for updating user information
    @PutMapping("/updateUsers/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Users users) {
        log.info("Inside updateUser method of UserController (authentication)");
        try {
            Users updateUser = userService.updateUser(id, users);
            return ResponseEntity.ok(updateUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //Api for deleting user
    @DeleteMapping("/deleteUsers/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Inside deleteUser method of UserController");
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
