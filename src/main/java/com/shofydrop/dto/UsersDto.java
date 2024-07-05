package com.shofydrop.dto;

import com.shofydrop.enumerated.LoginType;
import com.shofydrop.enumerated.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UsersDto {
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private LoginType loginType;
}
