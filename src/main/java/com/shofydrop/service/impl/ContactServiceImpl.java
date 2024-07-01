package com.shofydrop.service.impl;

import com.shofydrop.entity.Contact;
import com.shofydrop.repository.ContactRepository;
import com.shofydrop.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new
                RuntimeException("Contact does not exist with id" + id));
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact, Long id) {
        boolean isExist = contactRepository.existsById(id);
        if (isExist) {
            Contact isExistingContact = contactRepository.findById(id).get();
            isExistingContact.setPhoneNumber(contact.getPhoneNumber());
            isExistingContact.setEmail(contact.getEmail());
            isExistingContact.setAddress1(contact.getAddress1());
            isExistingContact.setAddress2(contact.getAddress2());
            isExistingContact.setCity(contact.getCity());
            isExistingContact.setState(contact.getState());
            isExistingContact.setCountry(contact.getCountry());
            isExistingContact.setCreatedAt(contact.getCreatedAt());
            isExistingContact.setUpdatedAt(contact.getUpdatedAt());
            return contactRepository.save(isExistingContact);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
