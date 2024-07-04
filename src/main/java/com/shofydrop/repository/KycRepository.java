package com.shofydrop.repository;

import com.shofydrop.entity.Kyc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycRepository extends JpaRepository<Kyc,Long> {
}
