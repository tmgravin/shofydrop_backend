package com.msp.shofydrop.orders.entity;

import com.msp.shofydrop.authentication.entity.Users;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(name = "send_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp sendAt;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updateAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Users sender;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Users receiver;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;
}