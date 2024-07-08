package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.Data;
<<<<<<< HEAD:src/main/java/com/shofydrop/entity/ProductImage.java
import java.time.LocalDateTime;
import java.util.TimeZone;
=======

import java.sql.Timestamp;
>>>>>>> origin/main:src/main/java/com/shofydrop/entity/ProductImages.java

@Data
@Entity
@Table(name="product_images")
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

<<<<<<< HEAD:src/main/java/com/shofydrop/entity/ProductImage.java
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
=======
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
>>>>>>> origin/main:src/main/java/com/shofydrop/entity/ProductImages.java
}
