package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.TimeZone;

@Data
@Entity
@Table(name = "stores")
public class Stores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "store_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String storeName;


    @Column(name = "store_description",columnDefinition = "TEXT", nullable = false)
    private String storeDescription;


    @Column(name = "store_logo", nullable = false, columnDefinition = "VARCHAR(255)")
    private String storeLogo;


    @Column(name = "store_banner", nullable = false,columnDefinition = "VARCHAR(255)")
    private String storeBanner;

    @Column(name = "is_open", nullable = false)
    private char isOpen = 'Y';
    @Column(name = "created_at", insertable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Users users;

    @PrePersist
    protected void onCreate() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}