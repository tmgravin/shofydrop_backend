package com.shofydrop.entity;

import com.shofydrop.enumerated.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oder {
    private Long id;
    private Users user;
//    private PromoCode promoCode;
//    private Vendor vendor;

    @Column(name = "total_price", columnDefinition = "DECIMAL(10,2)")
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", columnDefinition = "DEFAULT 'PENDING'")
    private OrderStatus orderStatus;
    @Column(name = "order_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp orderDate;
    @Column(name = "update_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updateAt;

}

