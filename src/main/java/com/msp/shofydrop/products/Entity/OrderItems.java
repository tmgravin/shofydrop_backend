package com.msp.shofydrop.products.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
public class OrderItems {
    @Id
    private Long id;

    private Long orderId;

    private Long productId;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal total;

    private Timestamp updateAt;

//    @Transient
//    private Order order;
}
