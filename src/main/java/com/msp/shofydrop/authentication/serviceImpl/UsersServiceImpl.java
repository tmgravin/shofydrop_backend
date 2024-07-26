package com.msp.shofydrop.authentication.serviceImpl;

import com.msp.shofydrop.authentication.entity.EmailVerificationToken;
import com.msp.shofydrop.authentication.entity.PasswordResetCode;
import com.msp.shofydrop.authentication.entity.UserDetails;
import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.repository.UserDetailsRepo;
import com.msp.shofydrop.authentication.repository.UserRepository;
import com.msp.shofydrop.authentication.repository.EmailVerificationRepo;
import com.msp.shofydrop.authentication.service.UsersService;
import com.msp.shofydrop.exception.EmailNotVerifiedException;
import com.msp.shofydrop.exception.OptimisticLockingException;
import com.msp.shofydrop.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailVerificationRepo emailVerificationRepo;

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

            emailVerificationRepo.saveToken(tokenEntity);

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
            EmailVerificationToken emailVerificationToken = emailVerificationRepo.findByToken(token).orElseThrow(()->
                    new IllegalStateException("Invalid verification token."));
            if (emailVerificationToken.getExpiredAt().toInstant().isBefore(Instant.now())){
                throw new IllegalStateException("Verification token expired.");
            }
            Users user = userRepository.findById(emailVerificationToken.getUserId()).orElseThrow(()->
                    new ResourceNotFoundException("User doesn't exist Id: " + emailVerificationToken.getUserId()));

            UserDetails userDetails = userDetailsRepo.findByUserId(emailVerificationToken.getUserId()).orElseThrow(()->
                    new ResourceNotFoundException("User details not found for user Id: " + emailVerificationToken.getUserId()));

            userDetails.setIsEmailVerified("Y");

            userRepository.saveUser(user);
            saveUserDetails(userDetails);
//            userDetailsRepo.saveUserDetails(userDetails);
            //If multiple token then latest token must be used for verification

        } catch (IllegalStateException | ResourceNotFoundException e) {
            throw e;
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new OptimisticLockingException("Failed to update user details. The record may have been modified or deleted by another transaction.", e);
        }catch (Exception e){
            throw new RuntimeException("Internal server error:" + e.getMessage(), e);
        }
    }

    @Transactional
    protected void saveUserDetails(UserDetails userDetails){
        userDetailsRepo.saveUserDetails(userDetails);
    }

    @Override
    public void forgetPassword(String email) {
        Users users = userRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("User doesn't exist with this email: " + email));
        //Check if users is verified or not
        UserDetails userDetails = userDetailsRepo.findByUserId(users.getId()).orElseThrow(()->
                new ResourceNotFoundException("User details not found for the user: " + email));
        if(userDetails.getIsEmailVerified() == "N"){
            throw new EmailNotVerifiedException("Email is not verified. Please verify your email before requesting for resetting password,");
        }
        //Generate verification code using UUID
        UUID uuid = UUID.randomUUID();
        long lsb = uuid.getLeastSignificantBits();
        int verificationCode = Math.abs((int) (lsb % 1000000));

        PasswordResetCode resetCode = new PasswordResetCode();
        resetCode.setCode(verificationCode);
        resetCode.setUserId(users.getId());
        resetCode.setExpiredAt(Timestamp.from(Instant.now().plusSeconds(3600)));

    }

    @Override
    public void verifyPasswordResetCode(int verificationCode) {

    }

    @Override
    public void resetPassword(String newPassword, String confirmPassword) {

    }

}
