package com.shofydrop.entity;

import com.shofydrop.enumerated.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.TimeZone;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Column(name = "order_date", nullable = false, updatable = false)
    private Timestamp orderDate;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Users vendors;

    @ManyToOne
    @JoinColumn(name = "promocode_id", referencedColumnName = "id")
    private PromoCode promoCode;

    @PrePersist
    protected void onCreate() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
        orderDate = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
