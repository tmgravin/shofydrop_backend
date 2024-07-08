package com.shofydrop.entity;

import com.shofydrop.enumerated.Status;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.TimeZone;

@Data
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;

    @OneToOne
    @JoinColumn(name = "delivery_boy_id", referencedColumnName = "id")
    private Users deliveryBoy;
}
