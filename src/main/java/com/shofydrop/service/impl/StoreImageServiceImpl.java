package com.shofydrop.service.impl;

import com.shofydrop.entity.StoreImage;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.StoreImageRepository;
import com.shofydrop.service.StoreImageService;
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
    public List<StoreImage> findAll() {
        return storeImageRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public StoreImage findById(Long id) {
        return storeImageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "store image does not exist with id" + id
                )
        );
    }

    /**
     * @param storeImage
     * @return
     */
    @Override
    public StoreImage save(StoreImage storeImage) {
        return storeImageRepository.save(storeImage);
    }

    /**
     * @param storeImage
     * @param id
     * @return
     */
    @Override
    public StoreImage update(StoreImage storeImage, Long id) {
        boolean isExist = storeImageRepository.existsById(id);
        if (isExist) {
            StoreImage existingStoreImage = storeImageRepository.findById(id).get();
            existingStoreImage.setImageUrl(storeImage.getImageUrl());
            existingStoreImage.setCreatedAt(storeImage.getCreatedAt());
            existingStoreImage.setUpdatedAt(storeImage.getUpdatedAt());
            return storeImageRepository.save(storeImage);
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
