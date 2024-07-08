package com.shofydrop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Data
@Entity
@Table(name= "vendor_kyc")
public class VendorKyc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_type", length = 50)
    private String documentType;

    @Column(name = "document_number",length = 50)
    private String documentNumber;

    @Column(name = "document_image_front", nullable = false)
    private String documentImageFront;

    @Column(name = "document_image_back", nullable = false)
    private String documentImageBack;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id", nullable = false)
    private Users users;
}
