package com.shofydrop.dto;

import com.shofydrop.enumerated.OrderStatus;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersDto {

    @DecimalMax("10")
    @DecimalMin("2")
    private double totalPrice;

    @Enumerated
    private OrderStatus orderStatus;

    @PastOrPresent
    private Timestamp orderDate;

    @FutureOrPresent
    private Timestamp updateAt;
}
