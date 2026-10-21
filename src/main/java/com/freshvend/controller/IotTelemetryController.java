package com.freshvend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freshvend.dto.ApiResponse;
import com.freshvend.dto.TelemetryRequest;

@RestController
@RequestMapping("/api/iot")
public class IotTelemetryController {

    @PostMapping("/telemetry")
    public ResponseEntity<ApiResponse> receiveTelemetry(@RequestBody(required = false) TelemetryRequest request) {

        // 1. Kiểm tra nếu body gửi lên bị rỗng hoàn toàn
        if (request == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("ERROR", "Request body không được để trống."));
        }

        // 2. Bắt buộc phải có mã thiết bị (deviceId)
        if (request.getDeviceId() == null || request.getDeviceId().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("ERROR", "Trường 'deviceId' là bắt buộc."));
        }

        // 3. Bắt buộc phải có giá trị nhiệt độ (temperature)
        if (request.getTemperature() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("ERROR", "Trường 'temperature' là bắt buộc."));
        }

        // 4. Kiểm tra giới hạn vật lý của cảm biến DHT22 (-40°C đến 80°C)
        if (request.getTemperature() < -40.0 || request.getTemperature() > 80.0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("ERROR", "Nhiệt độ ngoài dải đo hợp lệ của cảm biến (-40°C đến 80°C)."));
        }

        // 5. Kiểm tra độ ẩm nếu có gửi lên (nằm trong dải 0% đến 100%)
        if (request.getHumidity() != null && (request.getHumidity() < 0.0 || request.getHumidity() > 100.0)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("ERROR", "Độ ẩm phải nằm trong khoảng từ 0% đến 100%."));
        }

        // In nhật ký ra màn hình Terminal để quan sát kết quả trực tiếp
        System.out.println(">>> [IoT Telemetry] Nhận thành công từ: " + request.getDeviceId()
                + " | Nhiệt độ: " + request.getTemperature() + "°C"
                + " | Độ ẩm: " + request.getHumidity() + "%"
                + " | Thời gian: " + request.getTimestamp());

        // Phản hồi mã HTTP 200 OK
        return ResponseEntity.ok(new ApiResponse("SUCCESS", "Dữ liệu cảm biến đã được tiếp nhận thành công."));
    }
}