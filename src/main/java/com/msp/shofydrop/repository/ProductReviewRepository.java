package com.msp.shofydrop.repository;

import com.msp.shofydrop.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview,Long> {
}
