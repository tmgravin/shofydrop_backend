package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.Data;
<<<<<<< HEAD:src/main/java/com/shofydrop/entity/StoreImage.java
import java.time.LocalDateTime;
import java.util.TimeZone;
=======
>>>>>>> origin/main:src/main/java/com/shofydrop/entity/StoreImages.java


@Data
@Entity
@Table(name = "store_images")
public class StoreImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

<<<<<<< HEAD:src/main/java/com/shofydrop/entity/StoreImage.java
    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
=======
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp updatedAt;
>>>>>>> origin/main:src/main/java/com/shofydrop/entity/StoreImages.java

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Stores stores;
}
