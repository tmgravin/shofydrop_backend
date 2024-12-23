package com.msp.shofydrop.products.Entity;

import com.msp.shofydrop.stores.entity.StoreContact;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
//products.products
public class Products {

    @Id
    private long id;

    private Long subcategoryId;

    private Long categoryId;

    private String productName;

    private String description;

    private BigDecimal price;

    private Long stock;

    private BigDecimal discountedPrice;

    private Timestamp updatedAt;
    private Long vendorId;

}
