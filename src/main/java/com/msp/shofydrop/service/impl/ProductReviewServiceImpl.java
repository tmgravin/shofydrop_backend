package com.msp.shofydrop.service.impl;

import com.msp.shofydrop.entity.ProductReview;
import com.msp.shofydrop.exception.ResourceNotFoundException;
import com.msp.shofydrop.repository.ProductReviewRepository;
import com.msp.shofydrop.service.ProductReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    private ProductReviewRepository productReviewRepository;

    /**
     * @return
     */
    @Override
    public List<ProductReview> findAll() {
        return productReviewRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductReview findById(Long id) {
        return productReviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "ProductReview does not exist with id" + id
                ));
    }

    /**
     * @param productReview
     * @return
     */
    @Override
    public ProductReview save(ProductReview productReview) {
        return productReviewRepository.save(productReview);
    }

    /**
     * @param productReview
     * @param id
     * @return
     */
    @Override
    public ProductReview update(ProductReview productReview, Long id) {
        boolean isExist = productReviewRepository.existsById(id);
        if (isExist) {
            ProductReview isExistingProductReview = productReviewRepository.findById(id).get();
            isExistingProductReview.setRating(productReview.getRating());
            isExistingProductReview.setCreatedAt(productReview.getCreatedAt());
            isExistingProductReview.setUpdatedAt(productReview.getUpdatedAt());
            productReviewRepository.save(isExistingProductReview);
        }
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        productReviewRepository.deleteById(id);
    }
}
