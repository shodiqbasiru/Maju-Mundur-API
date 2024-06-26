package com.msfb.maju_mundur_application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private String id;
    private String customerName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trxDate;
    private List<TransactionDetailResponse> transactionDetails;
}
