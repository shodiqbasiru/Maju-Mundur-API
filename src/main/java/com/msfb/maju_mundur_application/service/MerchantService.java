package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.entity.Merchant;

import java.util.List;

public interface MerchantService {
    void createMerchant(Merchant merchant);
    Merchant getMerchantById(String id);
    List<Merchant> getAll();
    Merchant update(Merchant request);
    void delete(String id);
}
