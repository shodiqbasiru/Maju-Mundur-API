package com.msfb.maju_mundur_application.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRewardRequest {
    private String id;
    private String rewardName;
    private Integer point;
}
