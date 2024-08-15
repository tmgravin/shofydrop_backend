package com.msp.shofydrop.products.Dto;

import com.msp.shofydrop.products.Entity.OrderItems;
import lombok.Data;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
@Data
public class OrderDto {
    private Long id;

    private Long userId;

    private Long vendorId;

    private Timestamp orderDate;

    private String address;

    private BigDecimal totalPrice;

    private String paymentStatus;

    private String orderStatus;

    private String promocode;

    private Long rewardsPoint;

    private List<OrderItems> orderItems;
}
