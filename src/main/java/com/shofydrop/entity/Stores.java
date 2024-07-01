package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 6c10720 (all changes from start)

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "stores")
public class Stores {
<<<<<<< HEAD

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "store_name", nullable = false,columnDefinition = "VARCHAR(255)")
    private String storeName;

    @Column(name = "store_description",columnDefinition = "TEXT", nullable = false)
    private String storeDescription;

    @Column(name = "store_logo", nullable = false,columnDefinition = "VARCHAR(255)")
    private String storeLogo;

    @Column(name = "store_banner", nullable = false,columnDefinition = "VARCHAR(255)")
    private String storeBanner;

    @Column(name = "is_open", columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private char isOpen;

    @Column(name = "created_at", columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_name",nullable = false)
    private String storeName;
    @Column(name = "store_description", nullable = false)
    private String storeDescription;
    @Column(name = "store_logo", nullable = false)
    private String storeLogo;
    @Column(name = "store_banner", nullable = false)
    private String storeBanner;
    @Column(name = "is_open", columnDefinition = "CHAR(1) DEFAULT '0'")
    private char isOpen;
    @Column(name="created_at", columnDefinition=" TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp createdAt;
    @Column(name="updated_at", columnDefinition=" TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
>>>>>>> 6c10720 (all changes from start)
    private Users users;



<<<<<<< HEAD

=======
>>>>>>> 6c10720 (all changes from start)
}