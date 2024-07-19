package com.msp.shofydrop.service.impl;

import com.msp.shofydrop.entity.StoreContact;
import com.msp.shofydrop.exception.ResourceNotFoundException;
import com.msp.shofydrop.repository.StoreContactRepository;
import com.msp.shofydrop.service.StoreContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreContactServiceImpl implements StoreContactService {
    @Autowired
    private StoreContactRepository storeContactRepository;

   
    @Override
    public List<StoreContact> findAll() {
        return storeContactRepository.findAll();
    }


    @Override
    public StoreContact findById(Long id) {
        return storeContactRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "StoreContact does not exist with id " + id
                )
        );
    }


    @Override
    public StoreContact save(StoreContact storeContact) {
        return storeContactRepository.save(storeContact);
    }


    @Override
    public StoreContact update(StoreContact storeContact, Long id) {
        boolean isExist = storeContactRepository.existsById(id);
        if (isExist) {
            StoreContact isExistingStoreContact = storeContactRepository.findById(id).get();
            isExistingStoreContact.setAddress(storeContact.getAddress());
            isExistingStoreContact.setCity(storeContact.getCity());
            isExistingStoreContact.setState(storeContact.getState());
            isExistingStoreContact.setCreatedAt(storeContact.getCreatedAt());
            isExistingStoreContact.setUpdatedAt(storeContact.getUpdatedAt());
            return storeContactRepository.save(isExistingStoreContact);
        }
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {

    }
}
