package com.shofydrop.entity;

import com.shofydrop.enumerated.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name= "kyc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kyc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "kyc_id")
    private Long id;
    private Users user;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "document_file")
    private String documentFile;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "DEFAULT 'PENDING'")
    private Status status;
    @Column(name = "submitted_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp submittedAt;
    @Column(name = "verified_at", nullable = false, insertable = false)
    private Timestamp verifiedAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", nullable = false)
    private Timestamp updatedAt;

}
