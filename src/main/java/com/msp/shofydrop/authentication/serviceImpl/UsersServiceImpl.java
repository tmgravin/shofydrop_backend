package com.msp.shofydrop.authentication.serviceImpl;

import com.msp.shofydrop.authentication.entity.EmailVerificationToken;
import com.msp.shofydrop.authentication.entity.UserDetails;
import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.repository.UserDetailsRepo;
import com.msp.shofydrop.authentication.repository.UserRepository;
import com.msp.shofydrop.authentication.repository.VerificationTokenRepo;
import com.msp.shofydrop.authentication.service.UsersService;
import com.msp.shofydrop.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepo verificationTokenRepo;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private MailUtils mailUtils;

    //Implementation for get users by id and get all users
    @Override
    @Transactional
    public Optional<Users> getAllUsers(Long id) {
        return userRepository.findById(id);
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
            String savedUser = userRepository.saveUser(users);

            sendVerificationEmail(users.getEmail());
            return savedUser;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    //Implementation for user login
    @Override
    @Transactional
    public Users loginUser(String email, String password) {
        try {
            Users user = userRepository.findByEmail(email).orElseThrow(()->
                    new ResourceNotFoundException("Email don't match."));
            if(user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                UserDetails userDetails = new UserDetails();
                if(userDetails.getIsEmailVerified() == "N"){
                    throw new IllegalStateException("User is not verified, please verify your email first for login.");
                }
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
            Users user = userRepository.findByEmail(email).orElseThrow(()->
                    new ResourceNotFoundException("User doesn't exist with this email: " + email));

            String verificationToken = UUID.randomUUID().toString();
            String verificationLink = "http://localhost:8080/api/auth/users/verifyEmail?token=" + verificationToken;

            EmailVerificationToken tokenEntity = new EmailVerificationToken();
            tokenEntity.setToken(verificationToken);
            tokenEntity.setExpiredAt(Timestamp.from(Instant.now().plusSeconds(21600)));
            tokenEntity.setUserId(user.getId());

            verificationTokenRepo.saveToken(tokenEntity);

            mailUtils.emailVerificationToken(email, user.getName(), verificationLink);

        } catch (Exception e) {
            throw new RuntimeException("Internal server error: "+ e.getMessage());
        }
    }

    //Implementation for verifying email with token
    @Override
    @Transactional
    public void verifyEmailToken(String token) {
        try {
            EmailVerificationToken emailVerificationToken = verificationTokenRepo.findByToken(token).orElseThrow(()->
                    new IllegalStateException("Invalid verification token."));
            if (emailVerificationToken.getExpiredAt().toInstant().isBefore(Instant.now())){
                throw new IllegalStateException("Verification token expired.");
            }
            Users user = userRepository.findById(emailVerificationToken.getUserId()).orElseThrow(()->
                    new ResourceNotFoundException("User doesn't exist Id: " + emailVerificationToken.getUserId()));

            UserDetails userDetails = userDetailsRepo.findByUserId(emailVerificationToken.getUserId()).orElseThrow(()->
                    new ResourceNotFoundException("User details not found for user Id: " + emailVerificationToken.getUserId()));

            userDetails.setIsEmailVerified("Y");
            user.setUpdatedAt(String.valueOf(Timestamp.from(Instant.now())));
            userDetails.setUpdatedAt(String.valueOf(Timestamp.from(Instant.now())));

            userRepository.saveUser(user);
            userDetailsRepo.saveUserDetails(userDetails);

        } catch (IllegalStateException e) {
            throw e;
        } catch (ResourceNotFoundException e) {
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Internal server error:" + e.getMessage());
        }
    }
}
