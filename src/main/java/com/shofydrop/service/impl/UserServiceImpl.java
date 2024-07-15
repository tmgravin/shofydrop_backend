package com.shofydrop.service.impl;

import com.shofydrop.entity.Users;
import com.shofydrop.entity.VerificationToken;
import com.shofydrop.enumerated.LoginType;
import com.shofydrop.enumerated.UserType;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.UsersRepository;
import com.shofydrop.repository.VerificationTokenRepository;
import com.shofydrop.service.UserService;
import com.shofydrop.utils.MailUtils;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    private UsersRepository usersRepository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HttpSession session;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private VerificationTokenRepository tokenRepository;

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

//            //Ensure default value are set
//            if (users.getIsKycCompleted() == '\0') {
//                users.setIsKycCompleted('N');
//            }
//            if(users.getIsKycApproved() == '\0'){
//                users.setIsKycApproved('N');
//            }
//            if (users.getIsEmailVerified() == '\0') {
//                users.setIsEmailVerified('N');
//            }
            if (users.getUserType() == null) {
                users.setUserType(UserType.USER);
            }
            if (users.getLoginType() == null) {
                users.setLoginType(LoginType.EMAIL);
            }

            Users savedUser = usersRepository.save(users);
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
            Users user = usersRepository.findByEmail(email).orElseThrow(() ->
                    new ResourceNotFoundException("User doesn't exist with this email: " + email));

            String verificationToken = UUID.randomUUID().toString();
            String verificationLink = "http://localhost:8080/users/api/verifyEmail?token=" + verificationToken;

            VerificationToken tokenEntity = new VerificationToken();
            tokenEntity.setToken(verificationToken);
            tokenEntity.setExpiryDate(Timestamp.from(Instant.now().plusSeconds(21600)));
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
            VerificationToken verificationToken = tokenRepository.findByToken(token).orElseThrow(() ->
                    new IllegalStateException("Invalid verification token."));
            if (verificationToken.getExpiryDate().toInstant().isBefore(Instant.now())) {
                throw new IllegalStateException("Verification token expired.");
            }
            Users user = verificationToken.getUser();

            if (user == null) {
                throw new ResourceNotFoundException("User doesn't exist with this email: " + user.getEmail());
            }
//            user.setIsEmailVerified('Y');
            user.setUpdatedAt(Timestamp.from(Instant.now()));
            usersRepository.save(user);

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
            Users user = usersRepository.findByEmail(email).orElseThrow(() ->
                    new ResourceNotFoundException("Email and Password don't match!"));
            if (user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
//                if (user.getIsEmailVerified() == 'N') {
//                    throw new IllegalStateException("User is not verified, please verify your email");
//                }
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

            //Generate verification code using UUID
//            Random random = new Random();
//            int verificationCode = random.nextInt(900000) + 100000;
            UUID uuid = UUID.randomUUID();
            long lsb = uuid.getLeastSignificantBits();
            int verificationCode = Math.abs((int) (lsb % 1000000));

            session.setAttribute("resetPasswordVerificationCode", verificationCode);
            session.setAttribute("resetPasswordEmail", email);
            usersRepository.save(user);

            // Send verification code to user's email
            mailUtils.forgetPasswordVerificationCode(email, verificationCode);
            log.info("Verification code send to a: {}", email);
        } catch (Exception e) {
            log.error("Error during forget password.", e);
            throw new RuntimeException("Internal server Error: " + e.getMessage());
        }
    }

    //Implementation for verifying password reset code
    @Override
    public void verifyPasswordResetCode(int verificationCode) {
        try {
            String storedEmail = (String) session.getAttribute("resetPasswordEmail");
            Integer storedVerificationCode = (Integer) session.getAttribute("resetPasswordVerificationCode");

            if (storedEmail == null || storedVerificationCode == null) {
                throw new IllegalStateException("Email or verification code not found in the session. User verification failed.");
            }
            if (storedVerificationCode == verificationCode) {
                log.info("Code verified successfully.");
            } else {
                throw new ResourceNotFoundException("Invalid Verification Code.");
            }
        } catch (Exception e) {
            log.error("Error during user verification.");
            throw new RuntimeException("Internal server error: " + e.getMessage());
        }
    }

    //Implementation for resetting password
    @Override
    public void resetPassword(String newPassword, String confirmPassword) {
        try {
            String email = (String) session.getAttribute("resetPasswordEmail");
            if (email == null) {
                throw new IllegalStateException("Email not found in the session. User verification failed.");
            }
            if (!newPassword.equals(confirmPassword)) {
                throw new IllegalArgumentException("Password do not match.");
            }
            Users user = usersRepository.findByEmail(email).orElseThrow(() ->
                    new ResourceNotFoundException("User doesn't exist with this email: " + email));
            user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            usersRepository.save(user);
            log.info("Password reset for user: {}", email);
            session.removeAttribute("resetPasswordVerificationCode");
            session.removeAttribute("resetPasswordEmail");
        } catch (Exception e) {
            log.error("Error during password reset", e);
            throw new RuntimeException("Internal Server Error: " + e.getMessage());
        }
    }
}