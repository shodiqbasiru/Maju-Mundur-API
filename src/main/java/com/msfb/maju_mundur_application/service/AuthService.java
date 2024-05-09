package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.dto.request.LoginRequest;
import com.msfb.maju_mundur_application.dto.request.RegisterCustomerRequest;
import com.msfb.maju_mundur_application.dto.request.RegisterMerchantRequest;
import com.msfb.maju_mundur_application.dto.response.LoginResponse;
import com.msfb.maju_mundur_application.dto.response.RegisterCustomerResponse;
import com.msfb.maju_mundur_application.dto.response.RegisterMerchantResponse;

public interface AuthService {
    RegisterCustomerResponse registerCustomer(RegisterCustomerRequest request);
    RegisterMerchantResponse registerMerchant(RegisterMerchantRequest request);
    LoginResponse login (LoginRequest request);
}
