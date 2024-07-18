package com.msp.shofydrop.service.impl;

import com.msp.shofydrop.entity.SubCategory;
import com.msp.shofydrop.exception.ResourceNotFoundException;
import com.msp.shofydrop.repository.SubCategoryRepository;
import com.msp.shofydrop.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    /**
     * @return
     */
    @Override
    public List<SubCategory> findAll() {
        return subCategoryRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SubCategory findById(Long id) {
        return subCategoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "id does not exist with id " + id
                )
        );
    }

    /**
     * @param subCategory
     * @return
     */
    @Override
    public SubCategory save(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    /**
     * @param subCategory
     * @param id
     * @return
     */
    @Override
    public SubCategory update(SubCategory subCategory, Long id) {
        boolean isExist = subCategoryRepository.existsById(id);
        if (isExist) {
            SubCategory isExistingSubCategory = subCategoryRepository.findById(id).get();
            isExistingSubCategory.setName(subCategory.getName());
            isExistingSubCategory.setCreatedAt(subCategory.getCreatedAt());
            isExistingSubCategory.setUpdatedAt(subCategory.getUpdatedAt());
            return subCategoryRepository.save(isExistingSubCategory);
        }
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        subCategoryRepository.deleteById(id);
    }
}
