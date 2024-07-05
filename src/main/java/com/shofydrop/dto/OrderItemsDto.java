package com.shofydrop.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemsDto {

    @NotBlank(message = "Quantity cannot be null")
    private int quantity;

    @DecimalMax("10")
    @DecimalMin("2")
    @NotBlank(message = "Price cannot be null")
    private double price;

    @PastOrPresent
    private Timestamp createdAt;

    @FutureOrPresent
    private Timestamp updateAt;
}
