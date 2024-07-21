package com.shofydrop.entity;

import com.shofydrop.enumerated.PaymentMethod;
import com.shofydrop.enumerated.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment", columnDefinition = "DECIMAL(10,2)", nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", columnDefinition = "VARCHAR(255) DEFAULT 'CASH_ON_DELIVERY'", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'", nullable = false)
    private DeliveryStatus deliveryStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", columnDefinition = "VARCHAR(255) DEFAULT 'ONLINE'", nullable = false)
    private String paymentType;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false, unique = true)
    private Orders orders;

}
