package com.msp.shofydrop.products.serviceImpl;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.products.Entity.Products;
import com.msp.shofydrop.products.Entity.SavedProduct;
import com.msp.shofydrop.products.repository.SavedProductRepo;
import com.msp.shofydrop.products.service.SavedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SavedProductServiceImpl implements SavedProductService {

    @Autowired
    private SavedProductRepo savedProductRepo;

    @Override
    @Transactional
    public Long addSavedProducts(SavedProduct savedProduct) {
        return savedProductRepo.save(savedProduct);
    }

    @Override
    @Transactional
    public Optional<SavedProduct> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<SavedProduct> findByUserId(Long id) {
        return savedProductRepo.findByUserId(id);
    }

    @Override
    @Transactional
    public List<SavedProduct> findByProduct(Products product) {
        return List.of();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }

    @Override
    @Transactional
    public void deleteByUser(Users user) {

    }

    @Override
    @Transactional
    public void deleteByProduct(Products product) {

    }

    @Override
    @Transactional
    public List<SavedProduct> findAll() {
        return List.of();
    }
}
