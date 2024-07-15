package com.shofydrop.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "user_detail")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "is_kycCompleted", columnDefinition = "CHAR(1) DEFAULT 'N'", nullable = false)
    private char isKycCompleted;

    @Column(name = "is_kycApproved", columnDefinition = "CHAR(1) DEFAULT 'N'", nullable = false)
    private char isKycApproved;

    @Column(name = "is_emailVerified", columnDefinition = "CHAR(1) DEFAULT 'N'", nullable = false)
    private char isEmailVerified;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
}
