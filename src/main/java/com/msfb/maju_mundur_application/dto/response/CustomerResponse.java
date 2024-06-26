package com.msfb.maju_mundur_application.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String customerName;
    private String phoneNumber;
    private String address;
    private String email;
}
