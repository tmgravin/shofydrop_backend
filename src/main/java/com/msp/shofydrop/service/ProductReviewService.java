package com.msp.shofydrop.service;

import com.msp.shofydrop.entity.ProductReview;

import java.util.List;

public interface ProductReviewService {
    List<ProductReview> findAll();

    ProductReview findById(Long id);

    ProductReview save(ProductReview productReview);

    ProductReview update(ProductReview productReview, Long id);

    void delete(Long id);
}
