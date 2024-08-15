package com.msp.shofydrop.products.repository;

import com.msp.shofydrop.products.Entity.Image;
import com.msp.shofydrop.products.Entity.Products;

import java.util.List;

public interface ProductsRepo {

    List<Products> getProducts(Long id);

    List<Image> getImages(Long id);

    Long saveProduct(Products products);

    List<Products> findBynameContaining(String name);

    void deleteProductProductById(Long productId);

    List<Products> findByNameContaining(String name);

    void deleteProduct(Integer productId);

    String saveImage(Image image);
}
