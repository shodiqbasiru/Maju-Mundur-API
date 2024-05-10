package com.msfb.maju_mundur_application.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RewardRequest {
    private String rewardName;
    private Integer point;
}
