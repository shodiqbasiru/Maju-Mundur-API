package com.msfb.maju_mundur_application.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomResponse <T>{
    private Integer statusCode;
    private String message;
    private T data;
}
