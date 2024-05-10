package com.msfb.maju_mundur_application.repository;

import com.msfb.maju_mundur_application.entity.TransactionReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRewardRepository extends JpaRepository<TransactionReward, String> {
}
