package com.shofydrop.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class MailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

//    Mail configuration for forget password code verification
    public void forgetPasswordVerificationCode(String toEmail, int verificationCode) throws MessagingException {
//        MimeMessage for supporting content in various format like text in plain, HTML, and other formats
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String subject = "Password verification code.";
        String content = "Your Verification code for password reset is: "+ verificationCode;

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(content, true);

        javaMailSender.send(mimeMessage);

    }
//  Mail configuration for user verification code
    public void emailVerificationCode(String toEmail, int verificationCode) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String subject = "Email verification for Shofydrop login.";
        String content = "Your Verification code for Shofydrop login is: "+ verificationCode;

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(content, true);

        javaMailSender.send(mimeMessage);

    }
}
