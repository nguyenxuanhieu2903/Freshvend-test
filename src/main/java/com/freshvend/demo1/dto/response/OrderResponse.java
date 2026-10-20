package com.freshvend.demo1.dto.response;
import com.freshvend.demo1.domain.enums.OrderStatus; import com.freshvend.demo1.domain.enums.PaymentStatus;
import java.math.BigDecimal;
public record OrderResponse(Long id, String orderCode, BigDecimal totalAmount,
                            OrderStatus orderStatus, PaymentStatus paymentStatus) {}
