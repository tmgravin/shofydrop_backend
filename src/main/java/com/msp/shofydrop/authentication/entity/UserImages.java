package com.msp.shofydrop.authentication.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class UserImages {
    @Id
    private Long id;
    private String imageUrl;
}
