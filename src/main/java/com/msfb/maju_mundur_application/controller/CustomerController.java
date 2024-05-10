package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.request.CustomerRequest;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.dto.response.CustomerResponse;
import com.msfb.maju_mundur_application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiRoute.API_CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<List<CustomerResponse>>> getAll() {
        List<CustomerResponse> responses = customerService.getAll();
        CustomResponse<List<CustomerResponse>> response = CustomResponse.<List<CustomerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all customer successfully")
                .data(responses)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<CustomerResponse>> getById(@PathVariable String id) {
        CustomerResponse response = customerService.getCustomerById(id);
        CustomResponse<CustomerResponse> customResponse = CustomResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get customer successfully")
                .data(response)
                .build();
        return ResponseEntity.ok(customResponse);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<CustomerResponse>> update(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.update(request);
        CustomResponse<CustomerResponse> customResponse = CustomResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Updated customer successfully")
                .data(response)
                .build();
        return ResponseEntity.ok(customResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomResponse<CustomerResponse>> delete(@PathVariable String id) {
        customerService.delete(id);
        CustomResponse<CustomerResponse> customResponse = CustomResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Deleted customer successfully")
                .build();
        return ResponseEntity.ok(customResponse);
    }

}
