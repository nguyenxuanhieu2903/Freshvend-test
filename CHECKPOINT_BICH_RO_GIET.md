# [PROGRESS CHECKPOINT — BÍCH RỜ GIÉT]
**Dự án:** FreshVend — Smart IoT-Enabled Automated Fruit Vending Machine  
**Học phần:** Lập trình Java (012012213602) | **GVHD:** ThS. Nguyễn Văn Chiến  
**Sinh viên:** Lê Nguyễn Nhật Trường | **MSSV:** 082206010882  
**Thời điểm lưu:** Tuần 1 (19/10/2026)

---

```text
[PROGRESS CHECKPOINT — BÍCH RỜ GIÉT]

Student: Lê Nguyễn Nhật Trường (MSSV: 082206010882)
OS: Windows
Study Time: 8 giờ / tuần
Goal: Trở thành Fullstack Developer đủ năng lực đi làm thực tế
Current Skill Level: Đã làm chủ Spring Boot REST API cơ bản, Validation, HTTP Status Codes, Thunder Client, Dockerfile, Wokwi Hardware Simulation

STACK:
Frontend: Thunder Client (test API), HTML5/CSS (chuẩn bị cho module giao diện)
Backend: Java 17, Spring Boot 3.2.5 (Spring Web, Maven, Tomcat cổng 8080)
Database: PostgreSQL (chuẩn bị kết nối ở Tuần 2–3 cùng BE2)
IoT / Hardware: ESP32 DevKit V1, DHT22 (GPIO 4), Servo SG90 (GPIO 13), Wokwi Simulator
DevOps & Tools: Docker (`Dockerfile`), VS Code, Git, GitHub

CAPSTONE:
Project: FreshVend — Máy bán trái cây tự động thông minh tích hợp IoT, quản lý tồn kho và chuỗi lạnh
Current Milestone: Checkpoint Tuần 1 — Khởi động, Thiết kế API Telemetry & Mô phỏng phần cứng IoT
Completed Features:
- REST Controller: `POST /api/iot/telemetry` (lắng nghe cổng 8080)
- DTO `TelemetryRequest.java` (4 trường: `deviceId`, `temperature`, `humidity`, `timestamp`)
- Response `ApiResponse.java` (chuẩn hóa `status`, `message`, `serverTime`)
- Validation kiểm tra dữ liệu đầu vào:
  + Chặn null body (HTTP 400 Bad Request)
  + Bắt buộc `deviceId` không được để trống (HTTP 400 Bad Request)
  + Bắt buộc `temperature` không được để trống (HTTP 400 Bad Request)
  + Kiểm tra dải đo vật lý cảm biến DHT22 [-40°C đến 80°C] (HTTP 400 Bad Request)
  + Kiểm tra dải độ ẩm [0% đến 100%] (HTTP 400 Bad Request)
- Thunder Client Test Suite: 5 kịch bản test biên và lỗi thực nghiệm
- Mô phỏng phần cứng Wokwi: ESP32 + DHT22 + Servo SG90 quay 90° nhả khay trái cây
- Đóng gói container: `Dockerfile` (OpenJDK 17 Alpine, EXPOSE 8080)
- Tài liệu hóa toàn diện: `README.md` và `BAO_CAO_CHECKPOINT_BE3_NHAT_TRUONG.md`

CURRENT LESSON:
Nghiệm thu toàn diện Tuần 1 phân hệ BE3 & Điều phối phần cứng IoT

CURRENT TASK:
Tham gia họp Checkpoint Tuần 1, báo cáo nghiệm thu theo kịch bản đã chuẩn bị

KNOWN BUGS:
Không còn bug. Server Spring Boot chạy ổn định, API phản hồi chỉ 6-7ms, Thunder Client test 100% đạt yêu cầu.

COMMON MISTAKES & LESSONS LEARNED:
- Khi bấm Run Spring Boot trong VS Code: Phải mở file `FreshVendApplication.java`, không bấm Run khi đang ở file `pom.xml`.
- Khớp nối dữ liệu (Data Contract): Tên trường trong gói tin JSON gửi từ Client/Wokwi (`deviceId`) phải khớp 100% từng chữ hoa/thường với thuộc tính Java trong lớp DTO.

WEAK AREAS:
- Thao tác dòng lệnh Terminal và quản lý tiến trình chạy nền (đã rèn luyện và tiến bộ rõ rệt).

NEXT STEP:
1. Tham gia buổi họp Checkpoint Tuần 1, trình bày theo kịch bản trong file `BAO_CAO_CHECKPOINT_BE3_NHAT_TRUONG.md`.
2. Tuần 2: Nhận linh kiện thật từ nhóm, nạp firmware Arduino C++ từ Wokwi sang ESP32 thật qua cáp USB Serial.
3. Tuần 2–3: Phối hợp với bạn Văn Hiếu (BE2) kết nối Spring Data JPA để lưu dữ liệu cảm biến vào bảng `telemetry_logs` của PostgreSQL.

IMPORTANT DECISIONS:
- Dùng `deviceId` làm mã định danh thống nhất cho gói tin cảm biến.
- Dùng Thunder Client tích hợp ngay trong VS Code để test API thay vì bật thêm Postman ngoài.
- Dùng nguồn 5V ngoài cho Servo và nối chung chân GND với ESP32 để bảo vệ mạch vi điều khiển.
- Đặt ngưỡng chuỗi lạnh an toàn 2.0°C – 8.0°C theo đúng đặc tả SRS.

GIT STATUS:
Branch: feature/be3-iot-telemetry
Ready to commit & merge to develop branch.

TUTOR RULES:
- Tên lưu checkpoint: BÍCH RỜ GIÉT (Big Project FreshVend).
```
