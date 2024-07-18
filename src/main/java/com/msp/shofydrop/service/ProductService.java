package com.msp.shofydrop.service;

import com.msp.shofydrop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    Product update(Product product);

    Void delete(Long id);

    List<Product> findByCategoryId(Long id);
}
