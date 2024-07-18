package com.msp.shofydrop.service;

import com.msp.shofydrop.entity.Stores;

import java.util.List;

public interface StoreService {
    List<Stores> findAll();

    Stores findById(Long id);

    Stores update(Stores stores, Long id);

    Stores save(Stores stores);

    void delete(Long id);
}
