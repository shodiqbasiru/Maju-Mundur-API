package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.dto.request.CustomerRequest;
import com.msfb.maju_mundur_application.dto.response.CustomerResponse;
import com.msfb.maju_mundur_application.entity.Account;
import com.msfb.maju_mundur_application.entity.Customer;
import com.msfb.maju_mundur_application.repository.CustomerRepository;
import com.msfb.maju_mundur_application.service.CustomerService;
import com.msfb.maju_mundur_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createCustomer(Customer customer) {
        customerRepository.saveAndFlush(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse getCustomerById(String id) {
        Customer customer = getById(id);
        return CustomerResponse.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .address(customer.getAddress())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll().stream()
                .map(customer -> CustomerResponse.builder()
                        .id(customer.getId())
                        .customerName(customer.getCustomerName())
                        .address(customer.getAddress())
                        .phoneNumber(customer.getPhoneNumber())
                        .build()).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse update(CustomerRequest request) {
        Customer customer = getById(request.getId());
        customer.setCustomerName(request.getCustomerName());
        customer.setAddress(request.getAddress());
        customer.setPhoneNumber(request.getPhoneNumber());
        customerRepository.saveAndFlush(customer);
        return CustomerResponse.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .address(customer.getAddress())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        Customer currentCustomer = getById(id);
        Account account = userService.getUserById(currentCustomer.getAccount().getId());
        account.setIsEnable(false);
    }
}
