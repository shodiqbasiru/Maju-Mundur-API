package com.msfb.maju_mundur_application.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterMerchantResponse {
    private String merchantName;
    private String username;
    private List<String> roles;
}
