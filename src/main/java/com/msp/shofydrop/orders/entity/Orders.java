package com.msp.shofydrop.orders.entity;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.enumerated.OrderStatus;
import com.msp.shofydrop.stores.entity.PromoCode;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
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

    @Column(name = "order_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp orderDate;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updateAt;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id", unique = true)
    private Users vendors;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "promocode_id", referencedColumnName = "id", unique = true, nullable = false)
    private PromoCode promoCode;

}

