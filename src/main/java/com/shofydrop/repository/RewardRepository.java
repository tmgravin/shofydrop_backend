package com.shofydrop.repository;

import com.shofydrop.entity.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Rewards, Long> {
}
