package com.msp.shofydrop.products.entity;

import com.msp.shofydrop.authentication.entity.Users;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Entity
@Table(name = "purchase_product")
public class PurchasedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
