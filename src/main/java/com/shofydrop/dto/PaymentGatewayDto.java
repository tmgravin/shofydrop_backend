package com.shofydrop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PaymentGatewayDto {
    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String qrCode;
}
