package com.shofydrop.dto;

import com.shofydrop.enumerated.LoginType;
import com.shofydrop.enumerated.UserType;
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

    private UserType USERTYPE;
    @NotBlank(message = "kycCompleted can not be null")

    private char kycCompleted;

    @PastOrPresent
    private Timestamp createdAt;

    @FutureOrPresent
    private Timestamp updatedAt;

    private LoginType loginType;
}
