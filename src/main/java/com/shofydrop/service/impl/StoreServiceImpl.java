package com.shofydrop.service.impl;

import com.shofydrop.entity.Stores;
import com.shofydrop.repository.StoreRepository;
import com.shofydrop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Stores> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Stores findById(Long id) {
        return storeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Store not found" + id));
    }

    @Override
    public Stores update(Stores stores, Long id) {
        boolean isExist = storeRepository.existsById(id);
        if (isExist) {
            Stores isExisting = storeRepository.findById(id).get();
            isExisting.setStoreName(stores.getStoreName());
            isExisting.setStoreDescription(stores.getStoreDescription());
            isExisting.setStoreCategory(stores.getStoreCategory());
            isExisting.setStoreLogo(stores.getStoreLogo());
            isExisting.setStoreBanner(stores.getStoreBanner());
            isExisting.setIsOpen(stores.getIsOpen());
            return storeRepository.save(isExisting);
        }
        return null;
    }

    @Override
    public Stores save(Stores stores) {
        return storeRepository.save(stores);
    }

    @Override
    public void delete(Long id) {
        storeRepository.deleteById(id);
    }
}