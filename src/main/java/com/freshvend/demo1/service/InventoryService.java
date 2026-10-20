package com.freshvend.demo1.service;
import com.freshvend.demo1.dto.request.RestockRequest;
public interface InventoryService { void restock(RestockRequest request, Long operatorId); void reserveOrderStock(Long orderId); void consumeReservation(Long orderId); void releaseReservation(Long orderId); }
