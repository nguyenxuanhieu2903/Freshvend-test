package com.freshvend.demo1.service;

import com.freshvend.demo1.dto.response.SalesSummaryResponse;
import java.time.LocalDate;

public interface ReportingService {
    SalesSummaryResponse summarizeSales(LocalDate from, LocalDate to);
}
