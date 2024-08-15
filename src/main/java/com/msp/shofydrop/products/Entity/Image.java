package com.msp.shofydrop.products.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Image {
    @Id
    private Long id;
    private Long productId;
    private String images;
}
