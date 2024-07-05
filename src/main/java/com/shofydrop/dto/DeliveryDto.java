package com.shofydrop.dto;

import com.shofydrop.enumerated.Status;
import jakarta.persistence.Enumerated;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;

public class DeliveryDto {

    @Enumerated
    private Status status;

    @PastOrPresent
    private Timestamp createdAt;

    @FutureOrPresent
    private Timestamp updatedAt;

}
