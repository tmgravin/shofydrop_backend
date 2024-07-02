package com.shofydrop.entity;

import com.shofydrop.enumerated.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = true)
    private Order oid;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, columnDefinition = "ENUM('CashOnDelivery','OnlinePayment')")
    private PaymentMethod paymentMethod;

    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone_number", columnDefinition = "VARCHAR(14)",nullable = false)
    private String phoneNumber;

    @Column(name = "created_at", columnDefinition ="TIMESTAMP DEFAULT CURRENT_TIMESTAMP" )
    private String createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private String updatedAt;
}
