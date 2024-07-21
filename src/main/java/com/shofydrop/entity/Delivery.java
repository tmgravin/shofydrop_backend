package com.shofydrop.entity;

import com.shofydrop.enumerated.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'", nullable = false)
    private DeliveryStatus deliveryStatus;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id", unique = true)
    private Orders orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_boy_id", referencedColumnName = "id")
    private Users deliveryBoy;

}
