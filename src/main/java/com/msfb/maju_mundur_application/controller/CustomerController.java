package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.request.CustomerRequest;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.dto.response.CustomerResponse;
import com.msfb.maju_mundur_application.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiRoute.API_CUSTOMER)
@Tag(name = "Customer", description = "API for customer")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Get All",
            description = "Get all customer"
    )
    @SecurityRequirement(name = "Authorization")
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

    @Operation(
            summary = "Get By Id",
            description = "Get customer by id"
    )
    @SecurityRequirement(name = "Authorization")
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

    @Operation(
            summary = "Update",
            description = "Update customer"
    )
    @SecurityRequirement(name = "Authorization")
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

    @Operation(
            summary = "Delete",
            description = "Delete customer by id"
    )
    @SecurityRequirement(name = "Authorization")
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
