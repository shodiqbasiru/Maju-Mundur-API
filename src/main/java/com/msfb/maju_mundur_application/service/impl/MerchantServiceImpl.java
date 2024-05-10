package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.entity.Merchant;
import com.msfb.maju_mundur_application.repository.MerchantRepository;
import com.msfb.maju_mundur_application.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createMerchant(Merchant merchant) {
        merchantRepository.saveAndFlush(merchant);
    }

    @Override
    public Merchant getMerchantById(String id) {
        return merchantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "merchant not found"));
    }
}
