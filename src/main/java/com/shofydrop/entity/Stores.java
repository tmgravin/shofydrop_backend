package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

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

    @Column(name = "is_open", columnDefinition = "CHAR(1) DEFAULT 'Y'",  nullable = false)
    private char isOpen;

    @Column(name = "created_at", columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id", nullable = false, unique = true)
    private Users users;
}