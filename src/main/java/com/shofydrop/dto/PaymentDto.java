package com.shofydrop.dto;

import com.shofydrop.enumerated.PaymentMethod;
import com.shofydrop.enumerated.Status;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDto {

    @NotBlank
    private double amount;

    @Enumerated
    private PaymentMethod paymentMethod;

    @Enumerated
    private Status status;

    @PastOrPresent
    private Timestamp createdAt;

    @FutureOrPresent
    private Timestamp updatedAt;

}
