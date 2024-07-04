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
@Table(name = "stores")
public class Stores {

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
<<<<<<< HEAD
    @Column(name = "is_open", columnDefinition = "char(1) DEFAULT 'Y'", nullable = false)
    private char isOpen;
    @Column(name = "created_at", columnDefinition = " CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at", columnDefinition = " CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
=======


    @Column(name = "is_open", columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private char isOpen;


    @Column(name = "created_at", columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp createdAt;


    @Column(name = "updated_at", columnDefinition = " TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
>>>>>>> c24224ba947faebef11b2095a462323b7d332f1e
    private Timestamp updatedAt;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Users users;
<<<<<<< HEAD
=======

<<<<<<< HEAD



>>>>>>> 9acf53a3cad8b60e47085ea77baac92c81e8afc9
=======
>>>>>>> c24224ba947faebef11b2095a462323b7d332f1e
}