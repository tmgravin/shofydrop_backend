package com.shofydrop.entity;

import com.shofydrop.enumerated.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "vendor_kyc")
public class Vendor_Kyc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_type", columnDefinition = "VARCHAR(50)")
    private String documentType;

    @Column(name = "document_number", columnDefinition = "VARCHAR(50)")
    private String documentNumber;

    @Column(name = "document_image_front", nullable = false)
    private String documentImageFront;

    @Column(name = "document_image_back", nullable = false)
    private String documentImageBack;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id", nullable = false)
    private Users users;

}
