package com.freshvend.demo1.service;
import com.freshvend.demo1.dto.request.PaymentRequest; import com.freshvend.demo1.dto.response.PaymentResponse;
public interface PaymentService { PaymentResponse processSimulatedPayment(Long orderId, PaymentRequest request); }
