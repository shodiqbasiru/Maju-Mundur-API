package com.msfb.maju_mundur_application.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String productName;
    private Integer price;
    private Integer stock;
    private Boolean isDelete;
    private String merchantId;
}
