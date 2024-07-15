package com.shofydrop.entity;

import com.shofydrop.enumerated.LoginType;
import com.shofydrop.enumerated.UserType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "email", unique = true, columnDefinition = "VARCHAR(255)", nullable = false)
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "is_kycCompleted", columnDefinition = "CHAR(1) DEFAULT 'N'", nullable = false)
    private char isKycCompleted;

    @Column(name = "is_kycApproved", columnDefinition = "CHAR(1) DEFAULT 'N'", nullable = false)
    private char isKycApproved;

    @Column(name = "is_emailVerified", columnDefinition = "CHAR(1) DEFAULT 'N'", nullable = false)
    private char isEmailVerified;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", columnDefinition = "VARCHAR(255) DEFAULT 'USER'", nullable = false)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", columnDefinition = "VARCHAR(255) DEFAULT 'EMAIL'", nullable = false)
    private LoginType loginType;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;
}