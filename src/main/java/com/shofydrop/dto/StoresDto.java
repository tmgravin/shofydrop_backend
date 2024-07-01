package com.shofydrop.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
=======
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
>>>>>>> 6c10720 (all changes from start)
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
<<<<<<< HEAD

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
=======
    @NotBlank(message = "Store description is mandatory")
    private String storeDescription;
    @NotBlank(message = "Store category is mandatory")
    private String storeCategory;
    @NotBlank(message = "Store address is mandatory")
    private String storeAddress;
    @NotBlank(message = "Store phone is mandatory")
    private String storePhone;
    @NotBlank(message = "Store email is mandatory")
    private String storeEmail;
    @NotBlank(message = "Store logo is mandatory")
    private String storeLogo;
    @NotBlank(message = "Store banner is mandatory")
    private String storeBanner;
    @NotNull(message = "is open can not be null")
    private char isOpen;
    @NotNull(message = "created date can not be null")
    private Timestamp createdAt;
    @NotNull(message = "updated date can not be null")
>>>>>>> 6c10720 (all changes from start)
    private Timestamp updatedAt;
}
