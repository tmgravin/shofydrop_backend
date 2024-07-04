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

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", columnDefinition ="ENUM('USER','ADMIN','EDITOR','DELIVERY_BOY','VENDOR')", nullable = false)
    private UserType userType;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", columnDefinition = "VARCHAR(255) DEFAULT 'EMAIL'", nullable = false)
    private LoginType loginType;

}

