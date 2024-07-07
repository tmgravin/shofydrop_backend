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
        return productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException
                        (
                                "Product does not exist wwith id" + id
                        ));
    }


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Override
    public Product update(Product product, Long id) {
        boolean isExist = productRepository.existsById(id);
        if (isExist) {
            Product isExisstingProduct = productRepository.findById(id).get();
            isExisstingProduct.setDescription(product.getDescription());
            isExisstingProduct.setPrice(product.getPrice());
            isExisstingProduct.setStock(product.getStock());
            isExisstingProduct.setDiscountedPrice(product.getDiscountedPrice());
            isExisstingProduct.setCreatedAt(product.getCreatedAt());
            isExisstingProduct.setUpdatedAt(product.getUpdatedAt());
            return productRepository.save(isExisstingProduct);
        }
        return null;
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
