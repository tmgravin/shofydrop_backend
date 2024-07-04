package com.shofydrop.service.impl;

import com.shofydrop.entity.UsersContact;
import com.shofydrop.repository.ContactRepository;
import com.shofydrop.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    /**
     *
     * @return
     */

    @Override
    public List<UsersContact> findAll() {
        return contactRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */

    @Override
    public UsersContact findById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new
                RuntimeException("Contact does not exist with id" + id));
    }

    /**
     *
     * @param usersContact
     * @return
     */

    @Override
    public UsersContact save(UsersContact usersContact) {
        return contactRepository.save(usersContact);
    }

    @Override
    public UsersContact update(UsersContact usersContact, Long id) {
        boolean isExist = contactRepository.existsById(id);
        if (isExist) {
            UsersContact isExistingUsersContact = contactRepository.findById(id).get();
            isExistingUsersContact.setPhone(usersContact.getPhone());
            isExistingUsersContact.setEmail(usersContact.getEmail());
            isExistingUsersContact.setAddress(usersContact.getAddress());
            isExistingUsersContact.setCity(usersContact.getCity());
            isExistingUsersContact.setState(usersContact.getState());
            isExistingUsersContact.setCreatedAt(usersContact.getCreatedAt());
            isExistingUsersContact.setUpdatedAt(usersContact.getUpdatedAt());
            return contactRepository.save(isExistingUsersContact);
        }
        return null;
    }

    /**
     *
     * @param id
     */

    @Override
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
