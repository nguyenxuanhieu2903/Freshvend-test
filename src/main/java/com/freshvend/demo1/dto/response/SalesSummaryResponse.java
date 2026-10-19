package com.freshvend.demo1.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record SalesSummaryResponse(LocalDate from, LocalDate to, BigDecimal totalRevenue,
                                  long completedOrders, List<ProductRevenue> byProduct) {
    public record ProductRevenue(Long productId, String productName, BigDecimal revenue) {
    }
}
