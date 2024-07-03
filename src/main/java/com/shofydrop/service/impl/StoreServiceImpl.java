package com.shofydrop.service.impl;

import com.shofydrop.entity.Stores;
import com.shofydrop.repository.StoresRepository;
import com.shofydrop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoresRepository storesRepository;

    @Override
    public List<Stores> findAll() {
        return storesRepository.findAll();
    }

    @Override
    public Stores findById(Long id) {
        return storesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Store not found" + id));
    }

    @Override
    public Stores update(Stores stores, Long id) {
        boolean isExist = storesRepository.existsById(id);
        if (isExist) {
            Stores isExisting = storesRepository.findById(id).get();
            isExisting.setStoreName(stores.getStoreName());
            isExisting.setStoreDescription(stores.getStoreDescription());
            isExisting.setStoreCategory(stores.getStoreCategory());
            isExisting.setStoreLogo(stores.getStoreLogo());
            isExisting.setStoreBanner(stores.getStoreBanner());
            isExisting.setIsOpen(stores.getIsOpen());
            return storesRepository.save(isExisting);
        }
        return null;
    }

    @Override
    public Stores save(Stores stores) {
        return storesRepository.save(stores);
    }

    @Override
    public void delete(Long id) {
        storesRepository.deleteById(id);
    }
}
