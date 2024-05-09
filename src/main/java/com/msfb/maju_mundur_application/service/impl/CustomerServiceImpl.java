package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.entity.Customer;
import com.msfb.maju_mundur_application.repository.CustomerRepository;
import com.msfb.maju_mundur_application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createCustomer(Customer customer) {
        customerRepository.saveAndFlush(customer);
    }
}