package com.shofydrop.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemsDto {
    private Long id;
    private int quantity;
    private double price;
    private Timestamp createdAt;
    private Timestamp updateAt;
}
