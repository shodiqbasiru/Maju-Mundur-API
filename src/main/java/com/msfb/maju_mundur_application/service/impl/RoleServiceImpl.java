package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.constant.UserRole;
import com.msfb.maju_mundur_application.entity.Role;
import com.msfb.maju_mundur_application.repository.RoleRepository;
import com.msfb.maju_mundur_application.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(UserRole userRole) {
        Role role = Role.builder().role(userRole).build();
        return roleRepository.findByRole(userRole).orElseGet(() -> roleRepository.saveAndFlush(role));
    }
}
