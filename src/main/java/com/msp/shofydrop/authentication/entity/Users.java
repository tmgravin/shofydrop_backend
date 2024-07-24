package com.msp.shofydrop.authentication.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Users {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private String userType;
    private String loginType;
    private String createdAt;
    private String updatedAt;
}