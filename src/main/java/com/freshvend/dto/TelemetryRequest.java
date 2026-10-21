package com.freshvend.dto;

public class TelemetryRequest {

    // 4 trường dữ liệu cảm biến theo hợp đồng API Tuần 1
    private String deviceId;
    private Double temperature;
    private Double humidity;
    private String timestamp;

    // Constructor mặc định (bắt buộc để Spring Boot đọc dữ liệu JSON)
    public TelemetryRequest() {
    }

    // Constructor có tham số
    public TelemetryRequest(String deviceId, Double temperature, Double humidity, String timestamp) {
        this.deviceId = deviceId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }

    // Các hàm Getter và Setter
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}