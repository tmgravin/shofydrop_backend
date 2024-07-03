package com.shofydrop.service;

import com.shofydrop.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<Category> findAll();

    public Category findById(Long id);

    public Category save(Category category);

    public Category update(Category category, Long id);

    public void delete(Long id);
}
