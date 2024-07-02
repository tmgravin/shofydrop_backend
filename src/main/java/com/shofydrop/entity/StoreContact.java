package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "store_contact")
public class StoreContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "contact_email")
    private String contactEmail;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stores store;

}
