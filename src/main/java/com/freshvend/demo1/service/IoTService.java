package com.freshvend.demo1.service;
import com.freshvend.demo1.dto.request.TelemetryRequest; import com.freshvend.demo1.dto.response.TelemetryResponse;
public interface IoTService { TelemetryResponse recordTelemetry(TelemetryRequest request); }
