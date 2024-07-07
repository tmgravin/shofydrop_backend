package com.shofydrop.entity;

import com.shofydrop.enumerated.LoginType;
import com.shofydrop.enumerated.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(name = "name", nullable = false,columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "email", unique = true,columnDefinition = "VARCHAR(255)",nullable = false)
=======
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 100, unique = true, nullable = false)
>>>>>>> e805ae5b71d401ac2381906962c127cf4f8d10a0
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

<<<<<<< HEAD
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
=======
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", columnDefinition ="ENUM('USER','ADMIN','EDITOR','DELIVERY_BOY','VENDOR')", nullable = false)
    private UserType userType;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
>>>>>>> e805ae5b71d401ac2381906962c127cf4f8d10a0
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", columnDefinition = "VARCHAR(255) DEFAULT 'EMAIL'", nullable = false)
    private LoginType loginType;

}

