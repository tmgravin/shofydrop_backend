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
@Table(name= "kyc")
public class Kyc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users users;

    @Column(name = "document_type", columnDefinition = "VARCHAR(50)")
    private String documentType;

    @Column(name = "document_number", columnDefinition = "VARCHAR(100)")
    private String documentNumber;

    @Column(name = "document_file")
    private String documentFile;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private Status status;

    @Column(name = "submitted_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp submittedAt;

    @Column(name = "verified_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp verifiedAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

}
