package com.shofydrop.service;

import com.shofydrop.entity.ProductReview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductReviewService {
    public List<ProductReview> findAll();

    public ProductReview findById(Long id);

    public ProductReview save(ProductReview productReview);

    public ProductReview update(ProductReview productReview, Long id);

    public void delete(Long id);
}
