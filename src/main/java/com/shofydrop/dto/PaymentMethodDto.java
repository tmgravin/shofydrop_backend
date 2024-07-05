package com.shofydrop.dto;

import com.shofydrop.enumerated.PaymentMethod;
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
public class PaymentMethodDto {
    private Long id;
    private double amount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
