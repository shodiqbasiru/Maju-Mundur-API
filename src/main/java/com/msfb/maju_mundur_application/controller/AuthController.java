package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.request.LoginRequest;
import com.msfb.maju_mundur_application.dto.request.RegisterCustomerRequest;
import com.msfb.maju_mundur_application.dto.request.RegisterMerchantRequest;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.dto.response.LoginResponse;
import com.msfb.maju_mundur_application.dto.response.RegisterCustomerResponse;
import com.msfb.maju_mundur_application.dto.response.RegisterMerchantResponse;
import com.msfb.maju_mundur_application.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiRoute.API_AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping(
            path = "/register-customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<RegisterCustomerResponse>> registerCustomer(@RequestBody RegisterCustomerRequest request) {
        RegisterCustomerResponse customer = authService.registerCustomer(request);
        CustomResponse<RegisterCustomerResponse> response = CustomResponse.<RegisterCustomerResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Created customer successfully")
                .data(customer)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(
            path = "/register-merchant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<RegisterMerchantResponse>> registerCustomer(@RequestBody RegisterMerchantRequest request) {
        RegisterMerchantResponse merchant = authService.registerMerchant(request);
        CustomResponse<RegisterMerchantResponse> response = CustomResponse.<RegisterMerchantResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Created merchant successfully")
                .data(merchant)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<LoginResponse>> login(@RequestBody LoginRequest request){
        LoginResponse login = authService.login(request);
        CustomResponse<LoginResponse> response = CustomResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Login successfully")
                .data(login)
                .build();
        return ResponseEntity.ok(response);
    }

}
