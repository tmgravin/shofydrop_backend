package com.msp.shofydrop.authentication.service;

import com.msp.shofydrop.stores.entity.StoreContact;

import java.util.List;

public interface StoreContactService {
    List<StoreContact> findAll();

    StoreContact findById(Long id);

    StoreContact save(StoreContact storeContact);

    StoreContact update(StoreContact storeContact, Long id);

    void delete(Long id);
}
