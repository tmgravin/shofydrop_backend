package com.shofydrop.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
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
public class StoreImageDto {

    @NotBlank
    private String imageUrl;

    @PastOrPresent
    private Timestamp createdAt;

   @FutureOrPresent
    private Timestamp updatedAt;
}
