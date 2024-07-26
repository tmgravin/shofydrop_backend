package com.msp.shofydrop.authentication.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
//authentication.user_details
public class UserDetails {
    @Id
    private Long userId;
    private String isEmailVerified;
    private String isKycApproved;
    private String isKycCompleted;
    private Timestamp updatedAt;
}