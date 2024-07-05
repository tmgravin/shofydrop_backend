package com.shofydrop.entity;

import com.shofydrop.enumerated.LoginType;
import com.shofydrop.enumerated.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false,columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "email", unique = true,columnDefinition = "VARCHAR(255)",nullable = false)
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(20)")
    private String phone;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "postal_code", columnDefinition = "INT(6)")
    private String postalCode;

    @Column(name = "is_verified", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'N'")
    private char isVerified;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", columnDefinition = "VARCHAR(20) DEFAULT 'USER'")
    private Role role;

    @Column(name = "kyc_completed", columnDefinition = "CHAR(1) DEFAULT 'N'")
    private char kycCompleted;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_up_type", columnDefinition = "ENUM('FACEBOOK','GOOGLE','INSTAGRAM','LINKEDIN')")
    private LoginType loginType;

}

