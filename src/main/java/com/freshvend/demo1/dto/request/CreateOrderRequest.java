package com.freshvend.demo1.dto.request;
import java.util.List;
public record CreateOrderRequest(List<OrderItemRequest> items) {}
