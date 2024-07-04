package com.shofydrop.service;

import com.shofydrop.entity.StoreImage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreImageService {
    List<StoreImage> findAll();
    StoreImage findById(Long id);
    StoreImage save(StoreImage storeImage);
    StoreImage update(StoreImage storeImage,Long id);
    void delete(Long id);

}
