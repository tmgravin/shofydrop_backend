package com.msp.shofydrop.products.Entity;

import com.msp.shofydrop.authentication.entity.Users;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SavedProduct {
    @Id
    private Long id;

    private Long userId;

    private Long productId;
}
