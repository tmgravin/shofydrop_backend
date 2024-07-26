package com.msp.shofydrop.authentication.entity;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
//authentication.password_reset_code
public class PasswordResetCode {
    @Id
    private Long id;
    private int code;
    private String isVerified;
    private Timestamp expiredAt;
    private Long userId;
}