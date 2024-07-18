package com.msp.shofydrop.repository;

import com.msp.shofydrop.entity.PaymentGateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentGateWayRepository extends JpaRepository<PaymentGateway,Long> {
}
