package com.shofydrop.repository;

import com.shofydrop.entity.PaymentGateWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentGateWayRepository extends JpaRepository<PaymentGateWay,Long> {
}