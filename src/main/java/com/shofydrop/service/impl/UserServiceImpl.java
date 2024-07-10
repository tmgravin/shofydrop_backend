package com.shofydrop.service.impl;

import com.shofydrop.entity.Users;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.UsersRepository;
import com.shofydrop.service.UserService;
import com.shofydrop.utils.MailUtils;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HttpSession session;

    @Autowired
    private MailUtils mailUtils;

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(Long id) {
        try {
            return usersRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("user does not exist with this id " + id));
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + id + e.getMessage());
        }
    }

    @Override
    public Users update(Long id, Users users) {
        try {
            Users existingUser = usersRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("User not found"));
            existingUser.setName(users.getName());
            existingUser.setEmail(users.getEmail());
            existingUser.setPassword(DigestUtils.md5DigestAsHex(users.getPassword().getBytes()));
            existingUser.setKycCompleted(users.getKycCompleted());
            existingUser.setIsVerified(users.getIsVerified());
            existingUser.setUserType(users.getUserType());
            existingUser.setLoginType(users.getLoginType());
            existingUser.setUpdatedAt(Timestamp.from(Instant.now()));
            log.info("User Successfully Updated");
            return usersRepository.save(existingUser);
        } catch (Exception e) {
            log.error("Error updating user", e);
            throw new RuntimeException("Internal Server Error" + e.getMessage());
        }
    }

    @Override
    public Void delete(Long id) {
        try {
            usersRepository.deleteById(id);
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + id + e.getMessage());
        }
    }

    @Override
    public Users findByEmail(String email) {
        try {
            return usersRepository.findByEmail(email).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + email + e.getMessage());
        }
    }

    @Override
    public Users findByName(String name) {
        try {
            return usersRepository.findByName(name);
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + name + e.getMessage());
        }
    }

    //Logic for logging and signing user
    @Override
    public Users signupUser(Users users) {
        try {
            users.setPassword(DigestUtils.md5DigestAsHex(users.getPassword().getBytes()));
            return usersRepository.save(users);
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + users + e.getMessage());
        }
    }

    @Override
    public Users loginUser(String email, String password) {
        try {
            Users user = usersRepository.findByEmail(email).orElseThrow(()->
                    new ResourceNotFoundException("Email and Password don't match!"));
            if(user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                return user;
            }else {
                throw new ResourceNotFoundException("Email and password don't match!!");
            }
        }catch (Exception e){
            log.error("Error during login", e);
            throw new RuntimeException("Internal server Error: "+e.getMessage());
        }
    }

    //Implementation for forgetPassword, VerifyUser and ResetPassword
    @Override
    public void forgetPassword(String email) {
        try{
            Users user = usersRepository.findByEmail(email).orElseThrow(() ->
                    new ResourceNotFoundException("User doesn't exist with this email: "+email));
            Random random = new Random();
            int verificationCode = random.nextInt(90000) + 100000;
            session.setAttribute("resetPasswordVerificationCode", verificationCode);
            session.setAttribute("resetPasswordEmail", email);
            usersRepository.save(user);
            // Send verification code to user's email (implementation depends on your email service)
            mailUtils.sendVerificationCode(email, verificationCode);
            log.info("Verification code send to: {}", email);
        }catch (Exception e){
            log.error("Error during forget password.", e);
            throw new RuntimeException("Internal server Error: "+e.getMessage());
        }

    }

    @Override
    public void verifyCode(int verificationCode) {
        try {
            String storedEmail = (String) session.getAttribute("resetPasswordEmail");
            Integer storedVerificationCode = (Integer) session.getAttribute("resetPasswordVerificationCode");

            if(storedEmail == null || storedVerificationCode == null){
                throw new IllegalStateException("Email or verification code not found in the session. User verification failed.");
            }
            if (storedVerificationCode == verificationCode) {
                log.info("Code verified successfully.");
            }else{
                throw new ResourceNotFoundException("Invalid Verification Code.");
            }
        } catch (Exception e) {
            log.error("Error during user verification.");
            throw new RuntimeException("Internal server error: "+e.getMessage());
        }
    }

    @Override
    public void resetPassword(String newPassword, String confirmPassword) {
       try{
           String email = (String) session.getAttribute("resetPasswordEmail");
           if(email == null){
               throw new IllegalStateException("Email not found in the session. User verification failed.");
           }
           if(!newPassword.equals(confirmPassword)){
               throw new IllegalArgumentException("Password do not match.");
           }
           Users user = usersRepository.findByEmail(email).orElseThrow(() ->
                   new ResourceNotFoundException("User doesn't exist with this email: "+email));
           user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
           usersRepository.save(user);
           log.info("Password reset for user: {}",email);
           session.removeAttribute("resetPasswordVerificationCode");
           session.removeAttribute("resetPasswordEmail");
       }catch (Exception e){
           log.error("Error during password reset", e);
           throw new RuntimeException("Internal Server Error: "+e.getMessage());
       }
    }
}
