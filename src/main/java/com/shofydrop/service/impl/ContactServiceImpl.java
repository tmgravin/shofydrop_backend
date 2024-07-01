package com.shofydrop.service.impl;

import com.shofydrop.dto.ContactDto;
import com.shofydrop.service.ContactService;

import java.util.List;

public class ContactServiceImpl implements ContactService {

    @Override
    public List<ContactDto> findAllContact() {
        return List.of();
    }

    @Override
    public ContactDto findContactById(Long id) {
        return null;
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto, Long id) {
        return null;
    }

    @Override
    public ContactDto saveContact(ContactDto contactDto) {
        return null;
    }

    @Override
    public void deleteContact(Long id) {

    }
}
