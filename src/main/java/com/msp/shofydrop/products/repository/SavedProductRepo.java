package com.msp.shofydrop.products.repository;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.products.Entity.Products;
import com.msp.shofydrop.products.Entity.SavedProduct;

import java.util.List;
import java.util.Optional;

public interface SavedProductRepo {

    Long save(SavedProduct savedProduct);

    // Find a SavedProduct by ID
    Optional<SavedProduct> findById(Long id);

    // Find all SavedProducts by a specific user
    Optional<SavedProduct> findByUserId(Long id);

    // Find all SavedProducts by a specific product
    List<SavedProduct> findByProduct(Products product);

    // Delete a SavedProduct by ID
    void deleteById(Long id);

    // Delete all SavedProducts for a specific user
    void deleteByUser(Users user);

    // Delete all SavedProducts for a specific product
    void deleteByProduct(Products product);

    // Find all SavedProducts
    List<SavedProduct> findAll();
}
