package com.shofydrop.repository;

import com.shofydrop.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
