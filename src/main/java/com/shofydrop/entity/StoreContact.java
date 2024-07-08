package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.TimeZone;


@Data
@Entity
@Table(name = "store_contact")
public class StoreContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String postalCode;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "longitute")
    private double longitute;

    @Column(name = "latitute")
    private double latitute;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id",referencedColumnName = "id",unique = true)
    private Stores store;

    @PrePersist
    protected void onCreate() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}


