package com.msfb.maju_mundur_application.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRewardRequest {
    private String customerId;
    private String rewardId;
}
