package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.dto.request.TransactionRewardRequest;
import com.msfb.maju_mundur_application.dto.response.TransactionRewardResponse;
import com.msfb.maju_mundur_application.entity.TransactionReward;

import java.util.List;

public interface TransactionRewardService {
    TransactionRewardResponse createTrxReward(TransactionRewardRequest request);
    List<TransactionRewardResponse> getAllTrxReward();
}
