package com.shofydrop.repository;

import com.shofydrop.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<Contact, Integer> {
}
