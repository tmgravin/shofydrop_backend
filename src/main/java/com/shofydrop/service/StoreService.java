package com.shofydrop.service;

import com.shofydrop.entity.Stores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {
    List<Stores> findAll();
    Stores findById(Long id);
    Stores update(Stores stores, Long id);
    Stores save(Stores stores);
    void delete(Long id);
}