package com.shofydrop.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.sql.Timestamp;

public class ProductImageDto {

    @NotBlank(message = "image url cannot be null")
    private String imageUrl;

    @PastOrPresent
    private Timestamp createdAt;

    @FutureOrPresent
    private Timestamp updatedAt;
}
