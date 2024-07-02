package com.shofydrop.entity;

import com.shofydrop.enumerated.PaymentMethod;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CookieValue;

import java.sql.Timestamp;

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order oid;

    @Enumerated(EnumType.STRING)
    @Column(name = "paymentMethod", nullable = false, columnDefinition = "ENUM('CashOnDelivery','OnlinePayment')")
    private PaymentMethod paymentMethod;

    @Column(name = "payment", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double amount;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;
}
