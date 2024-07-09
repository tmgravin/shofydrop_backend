package com.shofydrop.service.impl;

import com.shofydrop.entity.Product;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.ProductRepository;
import com.shofydrop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product does not exist with this id " + id));
    }


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }


    @Override
    public Void delete(Long id) {
        productRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Product> findByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }
}
