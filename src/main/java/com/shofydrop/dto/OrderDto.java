package com.shofydrop.dto;

import com.shofydrop.enumerated.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {

    private Long id;
    private double totalPrice;
    private OrderStatus orderStatus;
    private Timestamp orderDate;
    private Timestamp updateAt;
}
