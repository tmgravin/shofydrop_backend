package com.msp.shofydrop.stores.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@Data
@Entity
//@Table(name = "store_contact")
public class StoreContact {
    @Id
    private Long id;

    private String phone;

    private String email;

    private String address;

    private String city;

    private String state;

    private double longitude;

    private double latitude;

    private Timestamp createdAt;

    private Timestamp updatedAt;
    private Long stores;
}