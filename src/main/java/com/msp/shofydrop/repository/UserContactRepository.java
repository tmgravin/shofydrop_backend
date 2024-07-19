package com.msp.shofydrop.repository;

import com.msp.shofydrop.entity.UsersContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactRepository extends JpaRepository<UsersContact, Long> {
}
