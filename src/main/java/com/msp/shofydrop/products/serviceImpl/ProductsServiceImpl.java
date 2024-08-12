package com.msp.shofydrop.products.serviceImpl;

import com.msp.shofydrop.exception.ResourceNotFoundException;
import com.msp.shofydrop.products.Entity.Products;
import com.msp.shofydrop.products.repository.ProductsRepo;
import com.msp.shofydrop.products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepo productsRepo;

    @Override
    @Transactional
    public Long addProducts(Products products) {
        return productsRepo.saveProduct(products);
    }

    @Override
    @Transactional
    public List<Products> getProducts(Long id) {
        return productsRepo.getProducts(id);
    }

    @Override
    @Transactional
    public List<Products> findProductsByName(String name) {
        return productsRepo.findBynameContaining(name);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        List<Products> product = productsRepo.getProducts(productId);

        if (product != null && !product.isEmpty()) {
            productsRepo.deleteProductProductById(productId);
        } else {
            throw new ResourceNotFoundException("Product with ID " + productId + " not found");
        }
    }
}
