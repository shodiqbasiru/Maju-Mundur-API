package com.msfb.maju_mundur_application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRewardResponse {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trxDate;
    private String customerName;
    private String rewardName;
}
