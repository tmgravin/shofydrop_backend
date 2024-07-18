package com.msp.shofydrop.repository;

import com.msp.shofydrop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
