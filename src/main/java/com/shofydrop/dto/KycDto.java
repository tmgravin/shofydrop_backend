package com.shofydrop.dto;

import com.shofydrop.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KycDto {
    private Long id;
    private String documentType;
    private String documentNumber;
    private String documentFile;
    private Status status;
    private Timestamp submittedAt;
    private Timestamp verifiedAt;
    private Timestamp updatedAt;
}
