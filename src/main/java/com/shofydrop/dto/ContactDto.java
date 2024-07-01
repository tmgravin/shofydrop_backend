package com.shofydrop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    private String phoneNumber;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
