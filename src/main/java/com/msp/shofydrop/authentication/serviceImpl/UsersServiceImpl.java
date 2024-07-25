package com.msp.shofydrop.authentication.serviceImpl;

import com.msp.shofydrop.authentication.entity.EmailVerificationToken;
import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.repository.UserRepository;
import com.msp.shofydrop.authentication.service.UsersService;
import com.msp.shofydrop.utils.MailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailUtils mailUtils;

    @Override
    @Transactional
    public List<Users> get(Long id) {
        return userRepository.getUsers(id);
    }
    //Implementation for user signup
    @Override
    @Transactional
    public String signupUser(Users users) {
        try {
            if(users.getPassword() == null || users.getPassword().isEmpty()){
                throw new IllegalArgumentException("Password cannot be empty.");
            }
            users.setPassword(DigestUtils.md5DigestAsHex(users.getPassword().getBytes()));
            sendVerificationEmail(users.getEmail());
            return userRepository.saveUser(users);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    //Implementation for user login
    @Override
    @Transactional
    public Users loginUser(String email, String password) {
        try {
            Users user = userRepository.findByEmail(email).orElseThrow(() ->
                    new ResourceNotFoundException("Email don't match."));
            if(user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                return user;
            }else {
                throw new ResourceNotFoundException("Password don't match.");
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Internal server error:" +e.getMessage());
        }
    }
    //Implementation for sending email verification token
    @Override
    @Transactional
    public void sendVerificationEmail(String email) {
        try {
            Users user = userRepository.findByEmail(email).orElseThrow(() ->
                    new ResourceNotFoundException("User doesn't exist with this email: " + email));

            String verificationToken = UUID.randomUUID().toString();
            String verificationLink = "http://localhost:8080/api/auth/users/verifyEmail?token=" +verificationToken;

            EmailVerificationToken tokenEntity = new EmailVerificationToken();
            tokenEntity.setToken(verificationToken);
            tokenEntity.setExpiredAt(Timestamp.from(Instant.now().plusSeconds(21600)));
            tokenEntity.setUserId(user.getId());

            mailUtils.emailVerificationToken(email, user.getName(), verificationLink);
        } catch (Exception e) {
            throw new RuntimeException("Internal server error: "+ e.getMessage());
        }
    }

    //Implementation for verifying email with token
    @Override
    public void verifyEmailToken(String token) {

    }
}
