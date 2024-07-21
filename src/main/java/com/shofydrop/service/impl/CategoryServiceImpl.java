package com.shofydrop.service.impl;

import com.shofydrop.entity.Category;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.CategoryRepository;
import com.shofydrop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("category does not exist with id" + id));
    }

    /**
     * @param category
     * @return
     */
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * @param category
     * @param id
     * @return
     */
    @Override
    public Category update(Category category, Long id) {
        boolean isExist = categoryRepository.existsById(id);
        if (isExist) {
            Category isExistingCategory = categoryRepository.findById(id).get();
            isExistingCategory.setName(category.getName());
            isExistingCategory.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            return categoryRepository.save(isExistingCategory);
        }
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

}
