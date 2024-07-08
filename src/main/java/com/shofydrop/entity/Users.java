package com.shofydrop.entity;

import com.shofydrop.enumerated.LoginType;
import com.shofydrop.enumerated.UserType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "email", unique = true, nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(20)")
    private String phone;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "postal_code", columnDefinition = "VARCHAR(6)")
    private String postalCode;

    @Column(name = "is_verified", nullable = false)
    private char isVerified = 'N';

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserType userType = UserType.USER;

    @Column(name = "kyc_completed", nullable = false)
    private char kycCompleted = 'N';

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_up_type", columnDefinition = "VARCHAR(255)")
    private LoginType loginType = LoginType.FACEBOOK;
}
