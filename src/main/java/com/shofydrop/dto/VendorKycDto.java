package com.shofydrop.dto;

import com.shofydrop.enumerated.Status;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VendorKycDto {
    private Long id;
    private String documentType;
    private String documentNumber;
    private String documentImageFront;
    private String documentImageBack;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
