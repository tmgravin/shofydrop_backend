package com.msp.shofydrop.products.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subcategory")
public class SubCategory {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subcategoryId;

//    @ManyToOne
//    @JoinColumn(name = "category_id", nullable = false)
//    private Category category;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

//    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Products> products;

    // Getters and Setters
}

