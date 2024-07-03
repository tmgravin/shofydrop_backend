package com.shofydrop.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProductDto {
    @NotBlank
    private String description;

    @NotNull
    private double price;

    @NotNull
    private int stock;

    @NotNull
    private double discountedPrice;

    @PastOrPresent
    private String createdAt;

    @FutureOrPresent
    private String updatedAt;
=======
public class ProductDto {
>>>>>>> kshitiz
}
