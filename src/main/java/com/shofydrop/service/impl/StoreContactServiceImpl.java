package com.shofydrop.service.impl;

import com.shofydrop.entity.StoreContact;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.StoreContactRepository;
import com.shofydrop.service.StoreContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreContactServiceImpl implements StoreContactService {
    @Autowired
    private StoreContactRepository storeContactRepository;

    /**
     * @return
     */
    @Override
    public List<StoreContact> findAll() {
        return storeContactRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public StoreContact findById(Long id) {
        return storeContactRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "StoreContact does not exist with id " + id
                )
        );
    }

    /**
     * @param storeContact
     * @return
     */
    @Override
    public StoreContact save(StoreContact storeContact) {
        return storeContactRepository.save(storeContact);
    }

    /**
     * @param storeContact
     * @param id
     * @return
     */
    @Override
    public StoreContact update(StoreContact storeContact, Long id) {
        boolean isExist=storeContactRepository.existsById(id);
        if (isExist){
            StoreContact isExistingStoreContact=storeContactRepository.findById(id).get();
           isExistingStoreContact.setContactEmail(storeContact.getContactEmail());
           isExistingStoreContact.setContactPhone(storeContact.getContactPhone());
           isExistingStoreContact.setAddress(storeContact.getAddress());
           isExistingStoreContact.setCity(storeContact.getCity());
           isExistingStoreContact.setState(storeContact.getState());
           isExistingStoreContact.setCountry(storeContact.getCountry());
           isExistingStoreContact.setPostalCode(storeContact.getPostalCode());
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
