package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersRepository {
//
//    Optional<Users> findByEmail(String email);
//
//    Users findByName(String name);

    List<Users> find(Long id);

}
