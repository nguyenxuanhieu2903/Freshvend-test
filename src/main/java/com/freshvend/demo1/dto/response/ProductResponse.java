package com.freshvend.demo1.dto.response;
import com.freshvend.demo1.domain.enums.ProductStatus; import java.math.BigDecimal;
public record ProductResponse(Long id, String name, String description, String imageUrl,
                              BigDecimal price, Integer availableQuantity, ProductStatus status) {}
