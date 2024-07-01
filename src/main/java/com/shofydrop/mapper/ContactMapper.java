package com.shofydrop.mapper;

import com.shofydrop.dto.ContactDto;
import com.shofydrop.entity.Contact;

public class ContactMapper {
    public static ContactDto mapToContactDto(Contact contact){
       return new ContactDto(
               contact.getId(),
               contact.getPhoneNumber(),
               contact.getEmail(),
               contact.getAddress1(),
               contact.getAddress2(),
               contact.getCity(),
               contact.getState(),
               contact.getPostalCode(),
               contact.getCountry(),
               contact.getCreatedAt(),
               contact.getUpdatedAt()
       );
    }
    public static Contact maptoContact(ContactDto contactDto){
        return new Contact(
                contactDto.getId(),
                contactDto.getPhoneNumber(),
                contactDto.getEmail(),
                contactDto.getAddress1(),
                contactDto.getAddress2(),
                contactDto.getCity(),
                contactDto.getState(),
                contactDto.getPostalCode(),
                contactDto.getCountry(),
                contactDto.getCreatedAt(),
                contactDto.getUpdatedAt()
        );
    }
}
