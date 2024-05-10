package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.dto.request.TransactionRequest;
import com.msfb.maju_mundur_application.dto.response.TransactionResponse;
import com.msfb.maju_mundur_application.dto.response.TransactionResultResponse;

import java.util.List;

public interface TransactionService {
    TransactionResultResponse createTransaction(TransactionRequest request);
    List<TransactionResponse> getAllTransaction();
}
