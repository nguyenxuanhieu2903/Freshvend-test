package com.freshvend.demo1.dto.request;
import com.freshvend.demo1.domain.enums.DataSourceType; import com.freshvend.demo1.domain.enums.DoorStatus;
import java.math.BigDecimal;
public record TelemetryRequest(String deviceCode, BigDecimal temperature, BigDecimal humidity,
                               DoorStatus doorStatus, DataSourceType dataSource) {}
