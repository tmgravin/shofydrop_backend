package com.shofydrop.service;

import com.shofydrop.entity.StoreContact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreContactService {
    public List<StoreContact> findAll();

    public StoreContact findById(Long id);

    public StoreContact save(StoreContact storeContact);

    public StoreContact update(StoreContact storeContact, Long id);

    public void delete(Long id);

}
