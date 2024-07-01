package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Long id;
    @Column(name="phone_number", nullable = false, length = 14)
    private String phoneNumber;
    @Column(name="email", nullable = false, unique = true)
    private String email;
    @Column(name="address_line1", nullable = false)
    private String address1;
    @Column(name="address_line2", nullable = false)
    private String address2;
    @Column(name="city", nullable = false)
    private String city;
    @Column(name="state", nullable = false)
    private String state;
    @Column(name="postal_code", nullable = false)
    private String postalCode;
    @Column(name="country", nullable = false)
    private String country;
<<<<<<< HEAD
    @Column(name="created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;
    @Column(name="updated_at", columnDefinition=" TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
=======
    @Column(name="created_at", columnDefinition=" TIMESTAMP Default Current_Timestamp", insertable = false)
    private Timestamp createdAt;
    @Column(name="updated_at", columnDefinition=" TIMESTAMP Default Current_Timestamp ON UPDATE Current_Timestamp", insertable = false)
>>>>>>> suraj
    private Timestamp updatedAt;

}
