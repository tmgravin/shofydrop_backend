package com.msp.shofydrop.authentication.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
//authentication.email_verification_token
public class EmailVerificationToken {
    @Id
    private Long id;
    private String token;
    private Timestamp expiredAt;
    private Long userId;
}
