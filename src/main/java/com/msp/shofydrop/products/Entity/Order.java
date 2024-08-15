package com.msp.shofydrop.products.Entity;

import com.msp.shofydrop.products.Enum.OrderStatus;
import lombok.Data;
import lombok.Lombok;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Order {
    @Id
    private Long id;

    private Long userId;

    private Long vendorId;

    private ZonedDateTime orderDate;

    private String address;

    private BigDecimal totalPrice;

//    private String paymentStatus;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String promocode;

    private Integer rewardsPoint;

    @Transient
    private List<OrderItems> orderItems = new ArrayList<>();}

