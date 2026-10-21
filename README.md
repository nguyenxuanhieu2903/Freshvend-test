# FreshVend Backend (BE3) — IoT Telemetry API
> **Dự án:** FreshVend — Máy bán trái cây tự động thông minh tích hợp IoT, quản lý tồn kho thời gian thực và chuỗi lạnh.  
> **Học phần:** Lập trình Java | **Mã học phần:** 012012213602  
> **Giảng viên hướng dẫn:** ThS. Nguyễn Văn Chiến  
> **Sinh viên thực hiện:** Lê Nguyễn Nhật Trường — Phụ trách Backend BE3 (IoT Telemetry API) & Điều phối IoT

---

## 📌 1. Giới thiệu Module BE3
Phân hệ **BE3 — IoT Telemetry API** đóng vai trò là "cầu nối" tiếp nhận dữ liệu thời gian thực từ phần cứng IoT (ESP32 + Cảm biến nhiệt ẩm DHT22), phục vụ việc giám sát chất lượng bảo quản trái cây theo tiêu chuẩn chuỗi lạnh (Cold Chain) từ **2.0°C đến 8.0°C**.

### Các tính năng cốt lõi đã hoàn thiện (Nghiệm thu Tuần 1):
1. **Tiếp nhận Telemetry (`POST /api/iot/telemetry`):** Hứng gói tin JSON từ ESP32 gửi lên qua giao thức HTTP REST.
2. **Kiểm tra hợp lệ dữ liệu đầu vào (Validation chặt chẽ):** Chặn các request rỗng, thiếu trường bắt buộc (`deviceId`, `temperature`) hoặc nhiệt độ vượt ngưỡng vật lý của cảm biến DHT22 (-40°C đến 80°C).
3. **Giám sát chuỗi lạnh (Cold-Chain Monitoring):** Tự động phát hiện và cảnh báo quá nhiệt (> 8°C) hoặc quá lạnh (< 2°C, nguy cơ đông đá làm hỏng trái cây tươi).
4. **Đồng bộ hóa 100% với Mạch mô phỏng Wokwi:** Cấu trúc DTO của Backend khớp chính xác từng ký tự với gói tin do ESP32 xuất ra.

---

## 🛠 2. Yêu cầu môi trường & Công nghệ
- **Ngôn ngữ:** Java 17 hoặc Java 21 (JDK)
- **Framework:** Spring Boot 3.2.4 (Spring Web, Spring Boot Starter Test)
- **Quản lý thư viện:** Maven
- **Công cụ soạn thảo:** Visual Studio Code / IntelliJ IDEA
- **Công cụ kiểm thử API:** Postman hoặc Thunder Client (VS Code Extension)
- **Mô phỏng IoT:** Nền tảng Wokwi (ESP32 + DHT22 + Servo SG90)

---

## 🚀 3. Hướng dẫn Khởi chạy Spring Boot Server

### Cách 1: Khởi chạy trực tiếp trong Visual Studio Code (Khuyến nghị)
1. Mở thư mục dự án trong VS Code.
2. Điều hướng đến file chính:  
   `src/main/java/com/freshvend/FreshVendApplication.java`
3. Nhấn vào nút **Run** (biểu tượng Play) ở góc trên bên phải hoặc nhấn tổ hợp phím **`F5`** (Start Debugging).
4. Quan sát cửa sổ **Terminal**, khi thấy dòng sau là Server đã sẵn sàng:
   ```text
   Tomcat started on port 8080 (http) with context path ''
   Started FreshVendApplication in 1.014 seconds
   ```

### Cách 2: Khởi chạy bằng dòng lệnh Terminal (Maven)
Mở Terminal ngay tại thư mục chứa file `pom.xml` và gõ lệnh:
```powershell
mvn spring-boot:run
```
*(Nếu dùng Maven Wrapper: `.\mvnw spring-boot:run`)*

---

## 📡 4. Hợp đồng API (API Specification & Contract)

### Endpoint 1: Tiếp nhận dữ liệu cảm biến
- **URL:** `http://localhost:8080/api/iot/telemetry`
- **Method:** `POST`
- **Headers:** `Content-Type: application/json`

#### Cấu trúc Request Body mẫu (Gửi từ ESP32 / Wokwi):
```json
{
  "deviceId": "ESP32_01",
  "temperature": 5.5,
  "humidity": 60.0,
  "timestamp": "2026-10-19T00:00:00Z"
}
```

#### Phản hồi khi thành công (HTTP 200 OK):
```json
{
  "status": "SUCCESS",
  "message": "Dữ liệu cảm biến đã được tiếp nhận thành công.",
  "serverTime": 1792346160172
}
```

#### Phản hồi khi thiếu dữ liệu (HTTP 400 Bad Request):
```json
{
  "status": "ERROR",
  "message": "Trường 'deviceId' là bắt buộc.",
  "serverTime": 1792346160172
}
```

#### Phản hồi khi nhiệt độ ngoài dải vật lý DHT22 (HTTP 400 Bad Request):
```json
{
  "status": "ERROR",
  "message": "Nhiệt độ ngoài dải đo hợp lệ của cảm biến (-40°C đến 80°C).",
  "serverTime": 1792346189937
}
```

---

## 🧪 5. Hướng dẫn Kiểm thử với Thunder Client / Postman

Bạn có thể mở tab **Thunder Client** trong VS Code hoặc mở ứng dụng **Postman** và thực hiện 4 Test Case sau:

| Test Case | Phương thức & URL | Payload JSON kiểm thử | Kết quả mong đợi |
| :--- | :--- | :--- | :--- |
| **TC 1: Đo bình thường** | `POST http://localhost:8080/api/iot/telemetry` | `{"deviceId": "ESP32_01", "temperature": 5.5, "humidity": 65.0, "timestamp": "2026-10-19T00:00:00Z"}` | `Status: 200 OK`, In log thành công ở Terminal. |
| **TC 2: Lỗi thiếu deviceId** | `POST http://localhost:8080/api/iot/telemetry` | `{"temperature": 15.5, "humidity": 65.0, "timestamp": "2026-10-19T00:55:00Z"}` | `Status: 400 Bad Request`, thông báo: *"Trường 'deviceId' là bắt buộc."* |
| **TC 3: Nhiệt độ vượt dải vật lý** | `POST http://localhost:8080/api/iot/telemetry` | `{"deviceId": "ESP32_01", "temperature": 150.0, "humidity": 65.0, "timestamp": "2026-10-19T00:55:00Z"}` | `Status: 400 Bad Request`, thông báo dải đo hợp lệ (-40°C đến 80°C). |
| **TC 4: Độ ẩm không hợp lệ** | `POST http://localhost:8080/api/iot/telemetry` | `{"deviceId": "ESP32_01", "temperature": 6.0, "humidity": 120.0, "timestamp": "2026-10-19T00:00:00Z"}` | `Status: 400 Bad Request`, thông báo độ ẩm phải từ 0% đến 100%. |

---

## 🔌 6. Mô phỏng Phần cứng IoT trên Wokwi

### Sơ đồ đấu nối mạch:
- **Vi điều khiển:** ESP32 DevKit V1 (30 chân).
- **Cảm biến DHT22:** Chân tín hiệu Data nối vào **GPIO 4**, VCC cấp 3.3V/5V, GND nối đất.
- **Động cơ Servo SG90:** Chân tín hiệu PWM nối vào **GPIO 13**, nguồn 5V ngoài cấp riêng, nối chung chân GND với ESP32.

### Quy trình hoạt động trên Wokwi:
1. ESP32 khởi động, kích hoạt Serial Monitor ở baud rate `115200`.
2. Đọc định kỳ nhiệt độ và độ ẩm từ cảm biến DHT22.
3. Đóng gói payload JSON theo định dạng chuẩn đã thống nhất:
   ```json
   {"deviceId":"ESP32_01","temperature":24.0,"humidity":40.0,"timestamp":"2026-10-19T00:00:00Z"}
   ```
4. Mô phỏng kích hoạt cơ cấu chấp hành: Servo quay 90° để nhả khay trái cây và đóng chốt về 0° an toàn.

---

## 📑 7. Đối chiếu Quy chuẩn Đặc tả Yêu cầu (SRS Compliance)
Mã nguồn module BE3 tuân thủ nghiêm ngặt các mục trong tài liệu đặc tả của nhóm:
- **FR-IOT-01 (Receive Sensor Data):** Tiếp nhận dữ liệu cảm biến môi trường.
- **FR-IOT-07 (Generate Temperature Alert):** Nhận diện điều kiện chuỗi lạnh 2°C - 8°C.
- **FR-IOT-12 (Handle Invalid Sensor Data):** Xử lý ngoại lệ, loại bỏ dữ liệu thiếu/sai định dạng.
- **BR-IOT-08 (Servo Actuation):** Cơ chế điều khiển góc quay servo nhả khay sản phẩm.
