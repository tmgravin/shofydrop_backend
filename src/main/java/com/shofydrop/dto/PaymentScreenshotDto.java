package com.shofydrop.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentScreenshotDto {
    private Long id;
    private String image;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
