package com.shofydrop.dto;

import com.shofydrop.enumerated.LoginType;
import com.shofydrop.enumerated.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Long id;
    @NotBlank(message = "name can not be null")
    private String name;
    @NotBlank(message = "email can not be null")
    private String email;
    @NotBlank(message = "password can not be null")
    private String password;
    @NotBlank(message = "phone can not be null")
    private String phone;
    @NotBlank(message = "address can not be null")
    private String address;
    @NotBlank(message = "isVerified can not be null")
    private String isVerified;
    @NotBlank(message = "role can not be null")
    private Role role;
    @NotBlank(message = "kycCompleted can not be null")
    private char kycCompleted;
    @NotNull(message = "createdAt can not be null")
    private Timestamp createdAt;
    @NotNull(message = "updatedAt can not be null")
    private Timestamp updatedAt;
    private LoginType loginType;

}
