package com.shofydrop.entity;

import com.shofydrop.enumerated.LoginType;
import com.shofydrop.enumerated.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name" ,nullable = false)
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "phone",nullable = false)
    private String phone;
    @Column(name = "address",nullable = false)
    private String address;
    @Column(name = "is_verified",nullable = false)
    private String isVerified;
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false, columnDefinition ="varchar default USER")
    private Role role;
    @Column(name = "kyc_completed", columnDefinition = "char(1) default 0" )
    private char kycCompleted;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "timestamp default timestamp",insertable = false)
    private Timestamp createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", columnDefinition = "timestamp default timestamp on update timestamp", insertable = false)
    private Timestamp updatedAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "login_type")
    private LoginType loginType;


    }

