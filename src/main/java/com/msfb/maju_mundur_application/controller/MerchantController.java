package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.entity.Merchant;
import com.msfb.maju_mundur_application.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiRoute.API_MERCHANT)
public class MerchantController {

    private final MerchantService merchantService;

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
