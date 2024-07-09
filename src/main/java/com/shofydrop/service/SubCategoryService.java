package com.shofydrop.service;

import com.shofydrop.entity.SubCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubCategoryService {
    public List<SubCategory> findAll();

    public SubCategory findById(Long id);

    public SubCategory save(SubCategory subCategory);

    public SubCategory update(SubCategory subCategory, Long id);

    public void delete(Long id);
}
