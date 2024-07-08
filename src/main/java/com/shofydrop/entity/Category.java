package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.TimeZone;

@Data
@Entity
@Table(name = "category")
public class Category {g
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
        updatedAt = LocalDateTime.now();
    }
}
