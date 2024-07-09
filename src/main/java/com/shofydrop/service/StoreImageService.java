package com.shofydrop.service;

import com.shofydrop.entity.StoreImages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreImageService {
    List<StoreImages> findAll();

    StoreImages findById(Long id);

    StoreImages save(StoreImages storeImages);

    StoreImages update(StoreImages storeImages, Long id);

    void delete(Long id);

}
