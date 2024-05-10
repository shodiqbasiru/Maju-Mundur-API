package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.entity.Merchant;
import com.msfb.maju_mundur_application.service.MerchantService;
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
@RequestMapping(path = ApiRoute.API_MERCHANT)
@Tag(name = "Merchant", description = "API for merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @Operation(
            summary = "Get All",
            description = "Get all merchant"
    )
    @SecurityRequirement(name = "Authorization")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<List<Merchant>>> getAll() {
        List<Merchant> responses = merchantService.getAll();
        CustomResponse<List<Merchant>> response = CustomResponse.<List<Merchant>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all merchant successfully")
                .data(responses)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get By Id",
            description = "Get merchant by id"
    )
    @SecurityRequirement(name = "Authorization")
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<Merchant>> getById(@PathVariable String id) {
        Merchant response = merchantService.getMerchantById(id);
        CustomResponse<Merchant> customResponse = CustomResponse.<Merchant>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get merchant successfully")
                .data(response)
                .build();
        return ResponseEntity.ok(customResponse);
    }

    @Operation(
            summary = "Update",
            description = "Update merchant"
    )
    @SecurityRequirement(name = "Authorization")
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<Merchant>> update(@RequestBody Merchant request) {
        Merchant response = merchantService.update(request);
        CustomResponse<Merchant> customResponse = CustomResponse.<Merchant>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Updated merchant successfully")
                .data(response)
                .build();
        return ResponseEntity.ok(customResponse);
    }

    @Operation(
            summary = "Delete",
            description = "Delete merchant by id"
    )
    @SecurityRequirement(name = "Authorization")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomResponse<Merchant>> delete(@PathVariable String id) {
        merchantService.delete(id);
        CustomResponse<Merchant> customResponse = CustomResponse.<Merchant>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Deleted merchant successfully")
                .build();
        return ResponseEntity.ok(customResponse);
    }
}
