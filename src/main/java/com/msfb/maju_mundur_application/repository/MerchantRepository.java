package com.msfb.maju_mundur_application.repository;

import com.msfb.maju_mundur_application.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant,String> {
}
