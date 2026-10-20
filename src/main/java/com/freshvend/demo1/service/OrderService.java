package com.freshvend.demo1.service;
import com.freshvend.demo1.dto.request.CreateOrderRequest; import com.freshvend.demo1.dto.response.OrderResponse;
public interface OrderService { OrderResponse createOrder(CreateOrderRequest request); OrderResponse getById(Long orderId); }
