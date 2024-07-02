package com.shofydrop.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ProductReviewDto {

    @Min(value = 0)
    @Max(value = 5)
    private int rating;

    @NotBlank
    private String comment;

    @Pattern(regexp = "[YN]", message = "KYC completed must be 'Y' or 'N'")
    private char kyeCompleted;

    @PastOrPresent
    private Timestamp createdAt;

    @FutureOrPresent
    private Timestamp updatedAt;

}
