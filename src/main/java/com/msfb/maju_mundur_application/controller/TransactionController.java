package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.request.TransactionRequest;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.dto.response.TransactionResponse;
import com.msfb.maju_mundur_application.dto.response.TransactionResultResponse;
import com.msfb.maju_mundur_application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiRoute.API_TRANSACTION)
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<TransactionResultResponse>> createTransaction(@RequestBody TransactionRequest request){
        TransactionResultResponse transaction = transactionService.createTransaction(request);
        CustomResponse<TransactionResultResponse> response = CustomResponse.<TransactionResultResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Created transaction successfully")
                .data(transaction)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<List<TransactionResponse>>> getAllTransaction() {
        List<TransactionResponse> transactions = transactionService.getAllTransaction();
        CustomResponse<List<TransactionResponse>> responses = CustomResponse.<List<TransactionResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all transaction successfully")
                .data(transactions)
                .build();
        return ResponseEntity.ok(responses);
    }
}
