package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
<<<<<<< HEAD
=======

>>>>>>> e805ae5b71d401ac2381906962c127cf4f8d10a0

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stores")
public class Stores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD

    @Column(name = "store_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String storeName;


    @Column(name = "store_description",columnDefinition = "TEXT", nullable = false)
    private String storeDescription;


    @Column(name = "store_logo", nullable = false, columnDefinition = "VARCHAR(255)")
    private String storeLogo;


    @Column(name = "store_banner", nullable = false,columnDefinition = "VARCHAR(255)")
    private String storeBanner;

    @Column(name = "is_open", columnDefinition = "char(1) DEFAULT 'Y'", nullable = false)
    private Boolean isOpen;
    @Column(name = "created_at", columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;
=======
    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "store_description", nullable = false)
    private String storeDescription;

    @Column(name = "store_category", nullable = false)
    private String storeCategory;

    @Column(name = "store_logo", nullable = false)
    private String storeLogo;

    @Column(name = "store_banner", nullable = false)
    private String storeBanner;

    @Column(name = "is_open")
    private boolean isOpen;

    @Column(name = "created_at", columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp createdAt;
>>>>>>> e805ae5b71d401ac2381906962c127cf4f8d10a0

    @Column(name = "updated_at", columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Users users;}