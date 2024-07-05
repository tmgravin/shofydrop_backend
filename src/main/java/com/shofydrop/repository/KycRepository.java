package com.shofydrop.repository;

import com.shofydrop.entity.Vendor_Kyc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycRepository extends JpaRepository<Vendor_Kyc,Long> {
}
