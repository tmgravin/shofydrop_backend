package com.msp.shofydrop.products.service;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.products.Entity.Products;
import com.msp.shofydrop.products.Entity.SavedProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface SavedProductService {
    // Adds a new saved product
    Long addSavedProducts(SavedProduct savedProduct);

    // Finds a saved product by its ID
    Optional<SavedProduct> findById(Long id);

    // Finds all saved products for a specific user
    Optional<SavedProduct> findByUserId(Long id);

    // Finds all saved products related to a specific product
    List<SavedProduct> findByProduct(Products product);

    // Deletes a saved product by its ID
    void deleteById(Long id);

    // Deletes all saved products for a specific user
    void deleteByUser(Users user);

    // Deletes all saved products related to a specific product
    void deleteByProduct(Products product);

    // Returns all saved products
    List<SavedProduct> findAll();
}
