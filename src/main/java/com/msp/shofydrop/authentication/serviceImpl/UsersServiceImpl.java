package com.msp.shofydrop.authentication.serviceImpl;

import com.msp.shofydrop.authentication.entity.EmailVerificationToken;
import com.msp.shofydrop.authentication.entity.PasswordResetCode;
import com.msp.shofydrop.authentication.entity.UserDetails;
import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.authentication.repository.PasswordResetRepo;
import com.msp.shofydrop.authentication.repository.UserDetailsRepo;
import com.msp.shofydrop.authentication.repository.UserRepository;
import com.msp.shofydrop.authentication.repository.EmailVerificationRepo;
import com.msp.shofydrop.authentication.service.UsersService;
import com.msp.shofydrop.exception.EmailRelatedException;
import com.msp.shofydrop.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailVerificationRepo emailVerificationRepo;

    @Autowired
    private PasswordResetRepo passwordResetRepo;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private MailUtils mailUtils;

    //Implementation for get users by id and get all users
    @Override
    @Transactional
    public Object getAllUsers(Long id) {
        try {
            if (id != null) {
                Optional<Users> user = userRepository.findById(id);
                return user.orElseThrow(() -> new ResourceNotFoundException("User not found with this id: " + id));
            } else {
                List<Users> user = userRepository.getAllUsers();
                if (user.isEmpty()) {
                    throw new ResourceNotFoundException("User list is null.");
                }
                return user;
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Internal server error: " + e.getMessage(), e);
        }
    }

    //Implementation for user signup
    @Override
    @Transactional
    public String signupUser(Users users) {
        try {
            if (users.getPassword() == null || users.getPassword().isEmpty()) {
                throw new IllegalArgumentException("Password cannot be empty.");
            }
            users.setPassword(DigestUtils.md5DigestAsHex(users.getPassword().getBytes()));
            String savedUser = userRepository.saveUser(users);

            sendVerificationEmail(users.getEmail());
            return savedUser;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Internal server error: " + e.getMessage(), e);
        }

    }

    //Implementation for sending email verification token
    @Override
    @Transactional
    public void sendVerificationEmail(String email) {
        try {
            Users user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with this email: " + email));

            UserDetails userDetails = userDetailsRepo.findByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User details not found for this userId: " + user.getId()));
            if (userDetails.getIsEmailVerified() == "Y") {
                throw new EmailRelatedException("Email is already verified. Please proceed for login.");
            }

            String verificationToken = UUID.randomUUID().toString();
            String verificationLink = "http://localhost:8080/api/auth/users/verifyEmail?token=" + verificationToken;

            EmailVerificationToken tokenEntity = new EmailVerificationToken();
            tokenEntity.setToken(verificationToken);
            tokenEntity.setExpiredAt(Timestamp.from(Instant.now().plusSeconds(21600)));
            tokenEntity.setUserId(user.getId());
            emailVerificationRepo.saveToken(tokenEntity);

            mailUtils.emailVerificationToken(email, user.getName(), verificationLink);

        } catch (ResourceNotFoundException | EmailRelatedException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Internal server error: " + e.getMessage(), e);
        }
    }

    //Implementation for verifying email with token
    @Override
    @Transactional
    public void verifyEmailToken(String token) {
        try {
            EmailVerificationToken emailVerificationToken = emailVerificationRepo.findByToken(token).orElseThrow(() -> new IllegalStateException("Invalid verification token."));

            List<EmailVerificationToken> allTokens = emailVerificationRepo.findAllTokensByUserId(emailVerificationToken.getUserId());

            if (allTokens.isEmpty()) {
                throw new IllegalStateException("No verification tokens found for user.");
            }
            EmailVerificationToken latestToken = allTokens.get(0);

            if (!latestToken.getToken().equals(token)) {
                throw new IllegalStateException("This is not the latest verification token.");
            }
            if (latestToken.getExpiredAt().toInstant().isBefore(Instant.now())) {
                throw new IllegalStateException("Verification token expired.");
            }
            UserDetails userDetails = userDetailsRepo.findByUserId(latestToken.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User details not found for user Id: " + latestToken.getUserId()));

            userDetails.setIsEmailVerified("Y");
            userDetailsRepo.saveUserDetails(userDetails);

        } catch (IllegalStateException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Internal server error:" + e.getMessage(), e);
        }
    }

    //Implementation for user login
    @Override
    @Transactional
    public Users loginUser(String email, String password) {
        try {
            Users user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email don't match."));
            if (user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
                UserDetails userDetails = new UserDetails();
                if (userDetails.getIsEmailVerified() == "N") {
                    throw new IllegalStateException("User is not verified, please verify your email before login.");
                }
                return user;
            } else {
                throw new ResourceNotFoundException("Password don't match.");
            }
        } catch (ResourceNotFoundException | IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Internal server error:" + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void forgetPassword(String email) {
        try {
            Users users = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with this email: " + email));
            //Check if users is verified or not
            UserDetails userDetails = userDetailsRepo.findByUserId(users.getId()).orElseThrow(() -> new ResourceNotFoundException("User details not found for this userId: " + users.getId()));
            if (userDetails.getIsEmailVerified() == "N") {
                throw new EmailRelatedException("Email is not verified. Please verify your email before requesting for resetting password,");
            }
            //Generate verification code using UUID
            UUID uuid = UUID.randomUUID();
            long lsb = uuid.getLeastSignificantBits();
            int verificationCode = Math.abs((int) (lsb % 1000000));

            PasswordResetCode resetCode = new PasswordResetCode();
            resetCode.setCode(verificationCode);
            resetCode.setUserId(users.getId());
            resetCode.setExpiredAt(Timestamp.from(Instant.now().plusSeconds(3600)));
            passwordResetRepo.saveCode(resetCode);

            //Send verification code
            mailUtils.forgetPasswordVerificationCode(email, users.getName(), verificationCode);
        } catch (EmailRelatedException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Internal server error: " + e.getMessage(), e);
        }
    }

    //Implementation for verifying password reset code
    @Override
    @Transactional
    public void verifyPasswordResetCode(int verificationCode) {
        try {
            PasswordResetCode resetCode = passwordResetRepo.findByCode(verificationCode).orElseThrow(() -> new IllegalStateException("Invalid password verification code."));
            List<PasswordResetCode> allCodes = passwordResetRepo.findAllCodesByUserId(resetCode.getUserId());

            if (allCodes.isEmpty()) {
                throw new IllegalStateException("No verification codes found for user.");
            }

            PasswordResetCode latestCode = allCodes.get(0);
            if (latestCode.getCode() != verificationCode) {
                throw new IllegalStateException("This is not the latest password reset code.");
            }
            if (resetCode.getExpiredAt().toInstant().isBefore(Instant.now())) {
                throw new IllegalStateException("Password verification code expired.");
            }
            UserDetails userDetails = userDetailsRepo.findByUserId(latestCode.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with Id: " + latestCode.getUserId()));
            resetCode.setIsVerified("Y");
            passwordResetRepo.saveCode(resetCode);
            userDetailsRepo.saveUserDetails(userDetails);

        } catch (ResourceNotFoundException | IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Internal server error: " + e.getMessage(), e);
        }
    }

    //Implementation for resetting password
    @Override
    @Transactional
    public void resetPassword(String newPassword, String confirmPassword) {
        try {
            if (!newPassword.equals(confirmPassword)) {
                throw new IllegalArgumentException("Password mismatch. New password and confirm password must be same.");
            }
            PasswordResetCode resetCode = passwordResetRepo.findByIsVerified("Y").orElseThrow(() -> new ResourceNotFoundException("Password reset code is not verified."));

            Users users = userRepository.findById(resetCode.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with this Id."));
            users.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            userRepository.saveUser(users);

            passwordResetRepo.deletePasswordResetCode(resetCode.getUserId());
        } catch (IllegalArgumentException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Internal server error: " + e.getMessage(), e);
        }
    }

    //Implementation for updating user
    @Override
    @Transactional
    public Users updateUser(Long id, Users users) {
        try {
            Users existingUser = userRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("User not found with id: " + id));
            existingUser.setName(users.getName());
            existingUser.setEmail(users.getEmail());

            userRepository.saveUser(existingUser);
            //Section for updating user's other information
            return existingUser;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Internal server error: " + e.getMessage(), e);
        }
    }

    //Implementation for deleting user
    @Override
    @Transactional
    public void deleteUser(Long id){
        try{
            Users user = userRepository.findById(id).orElseThrow(()->
                    new ResourceNotFoundException("User not found with id: " + id));
            userRepository.deleteUsers(id);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Internal server error: " + e.getMessage(), e);
        }
    }

}
