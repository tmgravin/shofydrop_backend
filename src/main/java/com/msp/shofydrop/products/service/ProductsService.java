package com.msp.shofydrop.products.service;

import com.msp.shofydrop.products.Entity.Products;

import java.util.List;


public interface ProductsService {

    Long addProducts(Products products);

    List<Products> getProducts(Long id);

    List<Products> findProductsByName(String name);

    void deleteProductById(Long productId);

}
