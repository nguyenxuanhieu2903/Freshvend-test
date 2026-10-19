package com.freshvend.demo1.dto.request;
import com.freshvend.demo1.domain.enums.PaymentMethod;
public record PaymentRequest(PaymentMethod paymentMethod) {}
