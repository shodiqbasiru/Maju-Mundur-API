package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.entity.Merchant;

public interface MerchantService {
    void createMerchant(Merchant merchant);
    Merchant getMerchantById(String id);
}
