package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.constant.UserRole;
import com.msfb.maju_mundur_application.entity.Role;

public interface RoleService {
    Role getOrSave(UserRole role);
}
