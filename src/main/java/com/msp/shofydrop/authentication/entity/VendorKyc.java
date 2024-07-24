package com.msp.shofydrop.authentication.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class VendorKyc {
    @Id
    private Long vendorId;
    private String documentNumber;
    private String documentType;
    private String documentImageBack;
    private String documentImageFront;
    private String createdAt;
    private String updatedAt;
}

