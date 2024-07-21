package com.shofydrop.service;

import com.shofydrop.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {
    List<SubCategory> findAll();

    SubCategory findById(Long id);

    SubCategory save(SubCategory subCategory);

    SubCategory update(SubCategory subCategory, Long id);

    void delete(Long id);
}
