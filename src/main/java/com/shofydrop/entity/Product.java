package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "discounted_price")
    private double discountedPrice;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "status", columnDefinition = "CHAR(1) DEFAULT '1'")
    private char status;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;
    @ManyToOne(cascade = CascadeType.ALL)
    private Stores stores;
    @ManyToOne(cascade = CascadeType.ALL)
    private Users users;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SubCategory> subCategory;


}
