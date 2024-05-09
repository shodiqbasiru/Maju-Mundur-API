package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.entity.Account;
import com.msfb.maju_mundur_application.entity.JwtClaims;

public interface JwtService {
    String generateToken(Account account);
    boolean verifyJwtToken(String token);
    JwtClaims getClaimsByToken(String token);
}
