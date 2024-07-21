package com.shofydrop.service;

import com.shofydrop.entity.StoreImages;

import java.util.List;

public interface StoreImageService {
    List<StoreImages> findAll();

    StoreImages findById(Long id);

    StoreImages save(StoreImages storeImages);

    StoreImages update(StoreImages storeImages, Long id);

    void delete(Long id);

}
