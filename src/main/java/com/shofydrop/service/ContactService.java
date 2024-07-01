package com.shofydrop.service;

import com.shofydrop.dto.ContactDto;

import java.util.List;

public interface ContactService {
    List<ContactDto> findAllContact();

    ContactDto findContactById(Long id);

    ContactDto updateContact(ContactDto contactDto, Long id);

    ContactDto saveContact(ContactDto contactDto);

    void deleteContact(Long id);
}
