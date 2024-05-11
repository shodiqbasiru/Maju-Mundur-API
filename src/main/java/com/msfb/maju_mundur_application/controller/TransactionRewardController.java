package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.request.TransactionRewardRequest;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.dto.response.TransactionRewardResponse;
import com.msfb.maju_mundur_application.service.TransactionRewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiRoute.API_TRANSACTION_REWARD)
@Tag(name = "Transaction Reward", description = "API for transaction reward")
public class TransactionRewardController {

    private final TransactionRewardService rewardService;

    @Operation(
            summary = "Create",
            description = "Create new transaction reward"
    )
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<TransactionRewardResponse>> create(@RequestBody TransactionRewardRequest request){
        TransactionRewardResponse trxReward = rewardService.createTrxReward(request);
        CustomResponse<TransactionRewardResponse> response = CustomResponse.<TransactionRewardResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Created transaction reward successfully")
                .data(trxReward)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Get All",
            description = "Get all transaction reward"
    )
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<List<TransactionRewardResponse>>> getAll() {
        List<TransactionRewardResponse> trxRewards = rewardService.getAllTrxReward();
        CustomResponse<List<TransactionRewardResponse>> responses = CustomResponse.<List<TransactionRewardResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all transaction reward successfully")
                .data(trxRewards)
                .build();
        return ResponseEntity.ok(responses);
    }
}
