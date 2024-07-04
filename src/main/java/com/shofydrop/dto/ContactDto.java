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
<<<<<<< HEAD
    @NotBlank(message = "phoneNumber can not be null and empty")
=======
>>>>>>> 88ef6e0 (contactService)
    private String phoneNumber;

    @NotBlank(message = "email can not be blank and empty")
    private String email;

    @NotBlank(message = "address1 can not be null and empty")
    private String address1;

    @NotBlank(message = "address2 can not be null and empty")
    private String address2;

    @NotBlank(message = "city can not be null and empty")
    private String city;

    @NotBlank(message = "state can not be empty and null")
    private String state;

    @NotBlank(message = "postalCode can not be empty and null")
    private String postalCode;

    @NotBlank(message = "country can not be null and empty")
    private String country;

    @PastOrPresent(message = "createAt must be current or past")
    private Timestamp createdAt;

    @FutureOrPresent(message = "updatedAt must be current or updated at")
    private Timestamp updatedAt;
}
