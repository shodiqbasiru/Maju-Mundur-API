package com.msfb.maju_mundur_application.service;

import com.msfb.maju_mundur_application.dto.request.RewardRequest;
import com.msfb.maju_mundur_application.dto.request.UpdateRewardRequest;
import com.msfb.maju_mundur_application.dto.response.RewardResponse;
import com.msfb.maju_mundur_application.entity.Reward;

import java.util.List;

public interface RewardService {
    Reward createReward(RewardRequest request);
    Reward getById(String id);
    RewardResponse getRewardById(String id);
    List<RewardResponse> getAllReward();
    RewardResponse updateReward(UpdateRewardRequest request);
    void delete(String id);
}
