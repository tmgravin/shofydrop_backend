package com.shofydrop.repository;

import com.shofydrop.entity.UsersContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<UsersContact, Long> {

}
