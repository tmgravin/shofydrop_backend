package com.shofydrop.service.impl;

import com.shofydrop.entity.PasswordResetCode;
import com.shofydrop.entity.UserDetails;
import com.shofydrop.entity.Users;
import com.shofydrop.entity.EmailVerificationToken;
import com.shofydrop.enumerated.LoginType;
import com.shofydrop.enumerated.UserType;
import com.shofydrop.exception.EmailNotVerifiedException;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.PasswordResetCodeRepository;
import com.shofydrop.repository.UserDetailsRepository;
import com.shofydrop.repository.UsersRepository;
import com.shofydrop.repository.VerificationTokenRepository;
import com.shofydrop.service.UserService;
import com.shofydrop.utils.MailUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordResetCodeRepository passwordResetCodeRepository;

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(Long id) {
        try {
            return usersRepository.findById(id).orElseThrow(()
                    -> new ResourceNotFoundException("user does not exist with this id " + id));
        } catch (Exception e) {
            throw new RuntimeException("Internal Server Error" + id + e.getMessage());
        }
    }

    @Override
    public Users update(Long id, Users users) {
        try {
            Users existingUser = usersRepository.findById(id).orElseThrow(()
                    -> new RuntimeException("User not found"));
            existingUser.setName(users.getName());
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

    //Implementation for user signup
    @Override
    public Users signupUser(Users users) {
        try {
            if (users.getPassword() == null || users.getPassword().isEmpty()) {
                throw new IllegalArgumentException("Password cannot be empty.");
            }
            users.setPassword(DigestUtils.md5DigestAsHex(users.getPassword().getBytes()));

            //Ensure default value are set for the user first
            if (users.getUserType() == null) {
                users.setUserType(UserType.USER);
            }
            if (users.getLoginType() == null) {
                users.setLoginType(LoginType.EMAIL);
            }
            //Save the users first to get the generated ID
            Users savedUser = usersRepository.save(users);

            //Now work for it child entity
            UserDetails userDetails = new UserDetails();
            userDetails.setIsKycCompleted('N');
            userDetails.setIsKycApproved('N');
            userDetails.setIsEmailVerified('N');

            userDetails.setUsers(savedUser);
            userDetailsRepository.save(userDetails);

            sendVerificationEmail(users.getEmail());
            return savedUser;
        } catch (Exception e) {
            throw new RuntimeException("Failed to signup user: " + e.getMessage());
        }
    }

    //Implementation for sending email verification code
    @Override
    public void sendVerificationEmail(String email) {
        try {
            Users user = usersRepository.findByEmail(email).orElseThrow(()
                    -> new ResourceNotFoundException("User doesn't exist with this email: " + email));

            String verificationToken = UUID.randomUUID().toString();
            String verificationLink = "http://localhost:8080/users/api/verifyEmail?token=" + verificationToken;

            EmailVerificationToken tokenEntity = new EmailVerificationToken();
            tokenEntity.setToken(verificationToken);
            tokenEntity.setExpiredAt(Timestamp.from(Instant.now().plusSeconds(21600)));
            tokenEntity.setUser(user);

            tokenRepository.save(tokenEntity);

            mailUtils.emailVerificationEmail(email, user.getName(), verificationLink);
            log.info("Verification link send to: {}", email);
        } catch (Exception e) {
            log.error("Error during sending verification email.", e);
            throw new RuntimeException("Internal Server Error:" + e.getMessage());
        }
    }

    //Implementation for verifying email with code
    @Override
    @Transactional
    public void verifyEmailToken(String token) {
        try {
            EmailVerificationToken emailVerificationToken = tokenRepository.findByToken(token).orElseThrow(() ->
                    new IllegalStateException("Invalid verification token."));
            if (emailVerificationToken.getExpiredAt().toInstant().isBefore(Instant.now())) {
                throw new IllegalStateException("Verification token expired.");
            }
            Users user = emailVerificationToken.getUser();
            if (user == null) {
                throw new ResourceNotFoundException("User doesn't exist with this email: " + user.getEmail());
            }

            UserDetails userDetails = userDetailsRepository.findByUsers(user).orElseThrow(() ->
                    new ResourceNotFoundException("User Details not found for user: "+ user.getEmail()));
            userDetails.setIsEmailVerified('Y');
            user.setUpdatedAt(Timestamp.from(Instant.now()));
            userDetails.setUpdatedAt(Timestamp.from(Instant.now()));

            usersRepository.save(user);
            userDetailsRepository.save(userDetails);

            tokenRepository.deleteByToken(token);

            log.info("User is verified: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Error during user verification.", e);
            throw new RuntimeException("Internal server error: " + e.getMessage());
        }
    }

    //Implementation for login user after verifying email
    @Override
    public Users loginUser(String email, String password) {
        try {
            Users user = usersRepository.findByEmail(email).orElseThrow(()
                    -> new ResourceNotFoundException("Email and Password don't match!"));
            if (user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
                UserDetails userDetails = new UserDetails();
                if (userDetails.getIsEmailVerified() == 'N') {
                    throw new IllegalStateException("User is not verified, please verify your email");
                }
                return user;
            } else {
                throw new ResourceNotFoundException("Email and password don't match!!");
            }
        } catch (Exception e) {
            log.error("Error during login", e);
            throw new RuntimeException("Internal server Error: " + e.getMessage());
        }
    }

    //Implementation for sending password reset code
    @Override
    public void forgetPassword(String email) {
        try {
            Users user = usersRepository.findByEmail(email).orElseThrow(() ->
                    new ResourceNotFoundException("User doesn't exist with this email: " + email));

            //Check if user is verified or not
            UserDetails userDetails = userDetailsRepository.findByUsers(user).orElseThrow(() ->
                    new ResourceNotFoundException("User details not found for the user: " + email));
            if (userDetails.getIsEmailVerified() == 'N') {
                throw new EmailNotVerifiedException("Email not verified. Please verify your email before requesting for resetting password.");
            }

            //Generate verification code using UUID
            UUID uuid = UUID.randomUUID();
            long lsb = uuid.getLeastSignificantBits();
            int verificationCode = Math.abs((int) (lsb % 1000000));

            PasswordResetCode resetCode = new PasswordResetCode();
            resetCode.setCode(verificationCode);
            resetCode.setIsVerified('N');
            resetCode.setUser(user);
            resetCode.setExpiredAt(Timestamp.from(Instant.now().plusSeconds(3600)));

            passwordResetCodeRepository.save(resetCode);

            //Send verification code to user's email
            mailUtils.forgetPasswordVerificationCode(email, user.getName(), verificationCode);
            log.info("Verification code send to a: {}", email);
        }catch (EmailNotVerifiedException e){
            log.error("Email not verified.", e);
            throw e;
        }catch (Exception e) {
            log.error("Error during forget password.", e);
            throw new RuntimeException("Internal server Error: " + e.getMessage());
        }
    }

    //Implementation for verifying password reset code
    @Override
    @Transactional
    public void verifyPasswordResetCode(int verificationCode) {
        try {
            PasswordResetCode resetCode = passwordResetCodeRepository.findByCode(verificationCode).orElseThrow(()
                    -> new ResourceNotFoundException("Invalid password verification code."));

            if (resetCode.getExpiredAt().toInstant().isBefore(Instant.now())) {
                throw new IllegalStateException("Password verification code expired.");
            }
            resetCode.setIsVerified('Y');
            passwordResetCodeRepository.save(resetCode);

            log.info("Code verified successfully for user: {}", resetCode.getUser().getEmail());
        } catch (Exception e) {
            log.error("Error during user verification.");
            throw new RuntimeException("Internal server error: " + e.getMessage());
        }
    }

    //Implementation for resetting password
    @Override
    @Transactional
    public void resetPassword(String newPassword, String confirmPassword) {
        try {
            if (!newPassword.equals(confirmPassword)) {
                throw new IllegalArgumentException("Password do not match.");
            }
            PasswordResetCode resetCode = passwordResetCodeRepository.findByIsVerified('Y').orElseThrow(()
                    -> new ResourceNotFoundException("No verified password reset code found."));

            Users user = resetCode.getUser();
            user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            usersRepository.save(user);

            log.info("Password reset for user: {}", user.getEmail());
            passwordResetCodeRepository.deleteByCode(resetCode.getCode());
        } catch (Exception e) {
            log.error("Error during password reset", e);
            throw new RuntimeException("Internal Server Error: " + e.getMessage());
        }
    }
}
