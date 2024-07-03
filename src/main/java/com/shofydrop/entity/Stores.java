package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "stores")
public class Stores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Column(name = "is_open", columnDefinition = "char(1) DEFAULT 'Y'", nullable = false)
    private char isOpen;
    @Column(name = "created_at", columnDefinition = " CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at", columnDefinition = " CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Users users;
<<<<<<< HEAD
=======




>>>>>>> 9acf53a3cad8b60e47085ea77baac92c81e8afc9
}