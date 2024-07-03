package com.shofydrop.entity;

import com.shofydrop.enumerated.OrderStatus;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price", columnDefinition = "DECIMAL(10,2)")
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private OrderStatus orderStatus;

    @Column(name = "ordered_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp orderDate;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updateAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Users users;
}

