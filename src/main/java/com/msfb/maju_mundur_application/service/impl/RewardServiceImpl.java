package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.dto.request.RewardRequest;
import com.msfb.maju_mundur_application.dto.request.UpdateRewardRequest;
import com.msfb.maju_mundur_application.dto.response.RewardResponse;
import com.msfb.maju_mundur_application.entity.Reward;
import com.msfb.maju_mundur_application.repository.RewardRepository;
import com.msfb.maju_mundur_application.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final RewardRepository rewardRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Reward createReward(RewardRequest request) {
        return rewardRepository.saveAndFlush(Reward.builder()
                .rewardName(request.getRewardName())
                .point(request.getPoint())
                .isDelete(false)
                .build());
    }

    @Transactional(readOnly = true)
    @Override
    public Reward getById(String id) {
        return rewardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "reward not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public RewardResponse getRewardById(String id) {
        Reward reward = getById(id);
        return RewardResponse.builder()
                .id(reward.getId())
                .rewardName(reward.getRewardName())
                .point(reward.getPoint())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<RewardResponse> getAllReward() {
        return rewardRepository.findAll().stream()
                .filter(reward -> !reward.getIsDelete())
                .map(reward -> RewardResponse.builder()
                        .id(reward.getId())
                        .rewardName(reward.getRewardName())
                        .point(reward.getPoint())
                        .build()).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RewardResponse updateReward(UpdateRewardRequest request) {
        Reward reward = getById(request.getId());
        reward.setRewardName(request.getRewardName());
        reward.setPoint(request.getPoint());
        rewardRepository.saveAndFlush(reward);
        return RewardResponse.builder()
                .id(reward.getId())
                .rewardName(reward.getRewardName())
                .point(reward.getPoint())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        Reward currentReward = getById(id);
        currentReward.setIsDelete(true);
    }
}
