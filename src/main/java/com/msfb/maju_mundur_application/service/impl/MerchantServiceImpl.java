package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.entity.Merchant;
import com.msfb.maju_mundur_application.repository.MerchantRepository;
import com.msfb.maju_mundur_application.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;

    @Override
    public void createMerchant(Merchant merchant) {
        merchantRepository.saveAndFlush(merchant);
    }
}
