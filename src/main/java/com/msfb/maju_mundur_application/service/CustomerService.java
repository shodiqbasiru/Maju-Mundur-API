package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.dto.request.CustomerRequest;
import com.msfb.maju_mundur_application.dto.response.CustomerResponse;
import com.msfb.maju_mundur_application.entity.Customer;

import java.util.List;

public interface CustomerService {
    void createCustomer(Customer customer);
    Customer getById(String id);
    CustomerResponse getCustomerById(String id);
    List<CustomerResponse> getAll();
    CustomerResponse update(CustomerRequest request);
    void delete(String id);
}
