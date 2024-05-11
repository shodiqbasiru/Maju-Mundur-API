package com.msfb.maju_mundur_application.dto.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private String customerId;
    private String merchantId;
    private List<TransactionDetailRequest> detailRequests;
}
