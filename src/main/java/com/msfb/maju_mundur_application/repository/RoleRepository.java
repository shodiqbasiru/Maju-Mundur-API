package com.msfb.maju_mundur_application.repository;

import com.msfb.maju_mundur_application.constant.UserRole;
import com.msfb.maju_mundur_application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByRole(UserRole role);
}
