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

<<<<<<< HEAD
    @Column(name = "contact_email",columnDefinition = "VARCHAR(255)", nullable = false)
    private String contactEmail;

    @Column(name = "contact_phone",columnDefinition = "VARCHAR(20)", nullable = false)
    private String contactPhone;

    @Column(name = "address",nullable = false,columnDefinition = "TEXT")
    private String address;

    @Column(name = "city",columnDefinition = "VARCHAR(100)",nullable = false)
    private String city;

    @Column(name = "state",nullable = false,columnDefinition = "VARCHAR(100)")
    private String state;

    @Column(name = "country",nullable = false,columnDefinition = "VARCHAR(100)")
    private String country;

    @Column(name = "postal_code",nullable = false,columnDefinition = "VARCHAR(20)")
=======
    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    @Column(name = "address", columnDefinition = "TEXT", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "postal_code", nullable = false)
>>>>>>> e805ae5b71d401ac2381906962c127cf4f8d10a0
    private String postalCode;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

<<<<<<< HEAD
    @Column(name = "longitute")
    private double longitute;

    @Column(name = "latitute")
    private double latitute;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id",referencedColumnName = "id",unique = true)
    private Stores store;
=======
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Stores stores;

>>>>>>> e805ae5b71d401ac2381906962c127cf4f8d10a0
}
