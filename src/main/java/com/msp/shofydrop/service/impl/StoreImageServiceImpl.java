package com.msp.shofydrop.service.impl;

import com.msp.shofydrop.exception.ResourceNotFoundException;
import com.msp.shofydrop.repository.StoreImageRepository;
import com.msp.shofydrop.service.StoreImageService;
import com.msp.shofydrop.entity.StoreImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreImageServiceImpl implements StoreImageService {
    @Autowired
    private StoreImageRepository storeImageRepository;

    /**
     * @return
     */
    @Override
    public List<StoreImages> findAll() {
        return storeImageRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public StoreImages findById(Long id) {
        return storeImageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "store image does not exist with id" + id
                )
        );
    }

    /**
     * @param storeImages
     * @return
     */
    @Override
    public StoreImages save(StoreImages storeImages) {
        return storeImageRepository.save(storeImages);
    }

    /**
     * @param storeImages
     * @param id
     * @return
     */
    @Override
    public StoreImages update(StoreImages storeImages, Long id) {
        boolean isExist = storeImageRepository.existsById(id);
        if (isExist) {
            StoreImages existingStoreImages = storeImageRepository.findById(id).get();
            existingStoreImages.setImageUrl(storeImages.getImageUrl());
            existingStoreImages.setCreatedAt(storeImages.getCreatedAt());
            existingStoreImages.setUpdatedAt(storeImages.getUpdatedAt());
            return storeImageRepository.save(storeImages);
        }
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        storeImageRepository.deleteById(id);
    }
}
