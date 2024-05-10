package com.msfb.maju_mundur_application.repository;

import com.msfb.maju_mundur_application.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<Reward, String> {
}
