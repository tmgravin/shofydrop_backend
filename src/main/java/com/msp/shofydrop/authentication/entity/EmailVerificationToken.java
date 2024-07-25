package com.msp.shofydrop.authentication.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class EmailVerificationToken {
    @Id
    private Long id;
    private String token;
    private String createdAt;
    private Timestamp expiredAt;
    private Long userId;
}
