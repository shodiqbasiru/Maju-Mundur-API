package com.msfb.maju_mundur_application.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RewardResponse {
    private String id;
    private String rewardName;
    private Integer point;
}
