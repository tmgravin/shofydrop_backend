package com.shofydrop.repository;

import com.shofydrop.entity.VendorKyc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycRepository extends JpaRepository<VendorKyc,Long> {
}
