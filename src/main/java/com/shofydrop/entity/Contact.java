package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name="contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="contact_id")
    private Long contactId;
    @Column(name="phone_number", columnDefinition = "VARCHAR(14) NOT NULL")
    private String phoneNumber;
    @Column(name="email", columnDefinition = "VARCHAR(255) NOT NULL")
    private String email;
    @Column(name="address_line1", columnDefinition = "VARCHAR(255) NOT NULL")
    private String address1;
    @Column(name="address_line2", columnDefinition = "VARCHAR(255)")
    private String address2;
    @Column(name="city", columnDefinition="VARCHAR(255) NOT NULL")
    private String city;
    @Column(name="state", columnDefinition="VARCHAR(255) NOT NULL")
    private String state;
    @Column(name="postal_code", columnDefinition="VARCHAR(255) NOT NULL")
    private String postalCode;
    @Column(name="country", columnDefinition="VARCHAR(255) NOT NULL")
    private String country;
    @Column(name="created_at", columnDefinition=" TIMESTAMP Default timestamp")
    private Timestamp createdAt;
    @Column(name="updated_at", columnDefinition=" TIMESTAMP Default timestamp ON UPDATE timestamp")
    private Timestamp updatedAt;

    private Long userId;
}
