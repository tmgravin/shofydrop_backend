package com.msp.shofydrop.service;

import com.msp.shofydrop.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    Category update(Category category, Long id);

    void delete(Long id);
}
