package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.entity.Account;
import com.msfb.maju_mundur_application.entity.Merchant;
import com.msfb.maju_mundur_application.repository.MerchantRepository;
import com.msfb.maju_mundur_application.service.MerchantService;
import com.msfb.maju_mundur_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createMerchant(Merchant merchant) {
        merchantRepository.saveAndFlush(merchant);
    }

    @Transactional(readOnly = true)
    @Override
    public Merchant getMerchantById(String id) {
        return merchantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "merchant not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Merchant update(Merchant request) {
        Merchant merchant = getMerchantById(request.getId());
        merchant.setMerchantName(request.getMerchantName());
        merchant.setAddress(request.getAddress());
        merchant.setPhoneNumber(request.getPhoneNumber());
        return merchantRepository.saveAndFlush(merchant);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        Merchant currentMerchant = getMerchantById(id);
        Account account = userService.getUserById(currentMerchant.getAccount().getId());
        account.setIsEnable(false);
    }
}
