package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Account getUserById(String id);
    Account getByContext();
}
