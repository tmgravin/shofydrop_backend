package com.shofydrop.service;

import com.shofydrop.entity.Stores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {
    public List<Stores> findAll();
    public Stores findById(Long id);
    public Stores update(Stores stores, Long id);
    public Stores save(Stores stores);
    public void delete(Long id);
}