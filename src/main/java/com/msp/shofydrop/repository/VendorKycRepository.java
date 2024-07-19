package com.msp.shofydrop.repository;

import com.msp.shofydrop.entity.VendorKyc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorKycRepository extends JpaRepository<VendorKyc, Long> {
}
