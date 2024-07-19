package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.UsersContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactRepository extends JpaRepository<UsersContact, Long> {
}
