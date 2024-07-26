package com.msp.shofydrop.authentication.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
//authentication.user_images
public class UserImages {
    @Id
    private Long id;
    private String imageUrl;
}
