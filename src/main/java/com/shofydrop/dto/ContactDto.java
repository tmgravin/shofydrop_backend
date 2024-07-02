package com.shofydrop.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactDto {
    @NotBlank(message = "phoneNumber can not be null and empty")
    private String phoneNumber;

    @NotBlank(message = "email can not be blank and empty")
    private String email;

    @NotBlank(message = "address1 can not be null and empty")
    private String address1;

    @NotBlank(message = "address2 can not be null and empty")
    private String address2;

    @NotBlank(message = "city can not be null and empty")
    private String city;

    @NotBlank(message = "state can not be empty nd null")
    private String state;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String country;

    @PastOrPresent
    private Timestamp createdAt;

    @FutureOrPresent
    private Timestamp updatedAt;
}
