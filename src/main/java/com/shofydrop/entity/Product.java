package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;


    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,2)")
=======
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
>>>>>>> e805ae5b71d401ac2381906962c127cf4f8d10a0
    private double price;

    @Column(name= "stock")
    private int stock;

    @Column(name = "discounted_price", columnDefinition = "DECIMAL(10,2)")
    private double discountedPrice;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Stores stores;

<<<<<<< HEAD
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "suc_category_id", referencedColumnName = "id")
    private SubCategory subCategory;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private Category category;


=======
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_category_id", referencedColumnName = "id")
    private SubCategory subCategory;
>>>>>>> e805ae5b71d401ac2381906962c127cf4f8d10a0
}
