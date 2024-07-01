package com.shofydrop.service;

import com.shofydrop.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {
    public List<Contact> findAll();
    public Contact findById(Long id);
    public Contact save(Contact contact);
    public Contact update(Contact contact, Long id);
    public void delete(Long id);

}
