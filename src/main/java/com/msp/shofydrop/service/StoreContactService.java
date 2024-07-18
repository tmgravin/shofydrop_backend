package com.msp.shofydrop.service;

import com.msp.shofydrop.entity.StoreContact;

import java.util.List;

public interface StoreContactService {
    List<StoreContact> findAll();

    StoreContact findById(Long id);

    StoreContact save(StoreContact storeContact);

    StoreContact update(StoreContact storeContact, Long id);

    void delete(Long id);

}
