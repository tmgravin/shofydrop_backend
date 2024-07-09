package com.shofydrop.service;

import com.shofydrop.entity.Stores;

import java.util.List;

public interface StoreService {
    public List<Stores> findAll();

    public Stores findById(Long id);

    public Stores update(Stores stores, Long id);

    public Stores save(Stores stores);

    public void delete(Long id);
}
