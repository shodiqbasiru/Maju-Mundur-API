package com.msfb.maju_mundur_application.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInfoResponse {
    private String id;
    private Integer reward;
    private Integer point;
}
