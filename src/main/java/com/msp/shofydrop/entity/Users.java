package com.msp.shofydrop.entity;

import com.msp.shofydrop.enumerated.LoginType;
import com.msp.shofydrop.enumerated.UserType;
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