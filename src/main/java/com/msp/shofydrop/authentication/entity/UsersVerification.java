package com.msp.shofydrop.authentication.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users_verification")
@Data
public class UsersVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false, length = 255)
    private String token;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "expired_at")
    private Timestamp expiredAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users users;

}
