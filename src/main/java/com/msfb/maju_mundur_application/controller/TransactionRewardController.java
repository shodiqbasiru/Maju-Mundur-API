package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.request.TransactionRewardRequest;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.dto.response.TransactionRewardResponse;
import com.msfb.maju_mundur_application.service.TransactionRewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiRoute.API_TRANSACTION_REWARD)
public class TransactionRewardController {

    private final TransactionRewardService rewardService;

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
