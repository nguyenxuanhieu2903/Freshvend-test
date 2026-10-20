package com.freshvend.demo1.service;
import com.freshvend.demo1.dto.response.ProductResponse;
import org.springframework.data.domain.Page; import org.springframework.data.domain.Pageable;
public interface ProductService { Page<ProductResponse> listActive(Pageable pageable); ProductResponse getById(Long productId); }
