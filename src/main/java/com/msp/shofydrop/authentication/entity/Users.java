package com.msp.shofydrop.authentication.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


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