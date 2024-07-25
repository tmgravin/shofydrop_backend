package com.msp.shofydrop.authentication.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserDetails {
    @Id
    private Long userId;
    private char isEmailVerified;
    private char isKycApproved;
    private char isKycCompleted;
    private String createdAt;
    private String updatedAt;
}