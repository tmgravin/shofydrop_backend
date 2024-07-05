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
public class PaymentMethodDto {
    private Long id;
    private double amount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
