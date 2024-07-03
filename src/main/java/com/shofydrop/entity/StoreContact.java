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

    @Column(name = "contact_email",nullable = false)
    private String contactEmail;

    @Column(name = "contact_phone",nullable = false)
    private String contactPhone;

    @Column(name = "address",nullable = false,columnDefinition = "text")
    private String address;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "state",nullable = false)
    private String state;

    @Column(name = "country",nullable = false)
    private String country;

    @Column(name = "postal_code",nullable = false)
    private String postalCode;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
<<<<<<< HEAD
    @JoinColumn(name = "store_id", referencedColumnName = "id")
=======
    @JoinColumn(name = "store_id",referencedColumnName = "id",unique = true)
>>>>>>> 9acf53a3cad8b60e47085ea77baac92c81e8afc9
    private Stores store;
}
