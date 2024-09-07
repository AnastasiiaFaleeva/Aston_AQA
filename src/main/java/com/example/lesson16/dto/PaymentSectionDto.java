package com.example.lesson16.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentSectionDto {

    private String paymentType;
    private String specialText;
    private String sum;
    private String email;
}
