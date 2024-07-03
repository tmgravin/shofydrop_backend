package com.shofydrop.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StoresDto {
    @NotBlank(message = "Store name is mandatory")
    private String storeName;

    @NotBlank(message = "Store description is mandatory")
    private String storeDescription;

    @NotBlank(message = "Store category is mandatory")
    private String storeCategory;

    @NotBlank(message = "Store logo is mandatory")
    private String storeLogo;

    @NotBlank(message = "Store banner is mandatory")
    private String storeBanner;

    @NotNull(message = "is open can not be null")
    private char isOpen;

    @PastOrPresent
    private Timestamp createdAt;

   @FutureOrPresent
    private Timestamp updatedAt;
}