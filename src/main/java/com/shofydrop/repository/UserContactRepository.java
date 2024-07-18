package com.shofydrop.repository;

import com.shofydrop.entity.UsersContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactRepository extends JpaRepository<UsersContact, Long> {
}