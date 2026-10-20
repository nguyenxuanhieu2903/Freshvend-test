package com.freshvend.demo1.dto.request;
import java.time.LocalDate;
public record RestockRequest(Long productId, Integer quantity, LocalDate expiryDate) {}
