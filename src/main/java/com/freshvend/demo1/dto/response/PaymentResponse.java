package com.freshvend.demo1.dto.response;
import com.freshvend.demo1.domain.enums.PaymentStatus;
public record PaymentResponse(Long orderId, String transactionCode, PaymentStatus status) {}
