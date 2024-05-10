package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.dto.request.TransactionRewardRequest;
import com.msfb.maju_mundur_application.dto.response.TransactionRewardResponse;
import com.msfb.maju_mundur_application.entity.Customer;
import com.msfb.maju_mundur_application.entity.Reward;
import com.msfb.maju_mundur_application.entity.TransactionReward;
import com.msfb.maju_mundur_application.repository.TransactionRewardRepository;
import com.msfb.maju_mundur_application.service.CustomerService;
import com.msfb.maju_mundur_application.service.RewardService;
import com.msfb.maju_mundur_application.service.TransactionRewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionRewardServiceImpl implements TransactionRewardService {

    private final TransactionRewardRepository trxRewardRepository;
    private final RewardService rewardService;
    private final CustomerService customerService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TransactionRewardResponse createTrxReward(TransactionRewardRequest request) {
        Reward reward = rewardService.getById(request.getRewardId());
        Customer customer = customerService.getById(request.getCustomerId());

        if (customer.getReward() - reward.getPoint() < 0)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "not enough points");

        customer.setReward(customer.getReward() - reward.getPoint());

        TransactionReward transactionReward = TransactionReward.builder()
                .date(new Date())
                .reward(reward)
                .customer(customer)
                .build();
        trxRewardRepository.saveAndFlush(transactionReward);


        return TransactionRewardResponse.builder()
                .id(transactionReward.getId())
                .trxDate(transactionReward.getDate())
                .customerName(transactionReward.getCustomer().getCustomerName())
                .rewardName(transactionReward.getReward().getRewardName())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransactionRewardResponse> getAllTrxReward() {
        return trxRewardRepository.findAll().stream()
                .map(transactionReward -> TransactionRewardResponse.builder()
                        .id(transactionReward.getId())
                        .trxDate(transactionReward.getDate())
                        .customerName(transactionReward.getCustomer().getCustomerName())
                        .rewardName(transactionReward.getReward().getRewardName())
                        .build()).toList();
    }
}
