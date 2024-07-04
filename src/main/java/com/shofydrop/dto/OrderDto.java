package com.shofydrop.dto;

import com.shofydrop.entity.Users;
import com.shofydrop.enumerated.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
