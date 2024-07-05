package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", nullable = false, length = 14)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "address_line1", nullable = false)
    private String address1;

    @Column(name = "address_line2")
    private String address2;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "state", nullable = false, length = 100)
    private String state;

    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name="updated_at", columnDefinition="TIMESTAMP Default Current_Timestamp ON UPDATE Current_Timestamp", insertable = false)
    private Timestamp updatedAt;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;


}
