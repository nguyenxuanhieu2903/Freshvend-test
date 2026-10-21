package com.freshvend.dto;

public class ApiResponse {

    private String status;    // "SUCCESS" hoặc "ERROR"
    private String message;   // Mô tả chi tiết kết quả xử lý
    private long serverTime;  // Thời điểm máy chủ xử lý xong

    public ApiResponse() {
    }

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
        this.serverTime = System.currentTimeMillis(); // Lấy thời gian thực tế của hệ thống
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }
}