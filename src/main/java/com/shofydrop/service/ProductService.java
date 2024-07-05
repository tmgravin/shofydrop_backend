package com.shofydrop.service;

import com.shofydrop.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> findAll();

    public Product findById(Long id);

    public Product save(Product product);

    public Product update(Product product, Long id);

    public Product delete(Long id);
}
