package com.msfb.maju_mundur_application.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterMerchantRequest {
    private String merchantName;
    private String username;
    private String password;
}
