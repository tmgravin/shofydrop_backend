package com.shofydrop.service;

<<<<<<< HEAD
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

=======
import com.shofydrop.dto.ContactDto;

import java.util.List;

public interface ContactService {
    List<ContactDto> findAllContact();

    ContactDto findContactById(Long id);

    ContactDto updateContact(ContactDto contactDto, Long id);

    ContactDto saveContact(ContactDto contactDto);

    void deleteContact(Long id);
>>>>>>> kshitiz
}
