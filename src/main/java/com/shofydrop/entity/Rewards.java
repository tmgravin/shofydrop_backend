package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.TimeZone;

@Data
@Entity
@Table(name = "rewards")
public class Rewards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "points", nullable = false)
    private int point;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;


    @PrePersist
    protected void onCreate() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
