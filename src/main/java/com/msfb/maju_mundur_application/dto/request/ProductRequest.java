package com.msfb.maju_mundur_application.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String productName;
    private Integer price;
    private Integer stock;
    private String merchantId;
}
