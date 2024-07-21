package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.VendorKyc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorKycRepository extends JpaRepository<VendorKyc, Long> {
}
