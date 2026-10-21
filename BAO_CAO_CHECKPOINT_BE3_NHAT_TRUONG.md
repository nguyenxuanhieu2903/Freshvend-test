# TÀI LIỆU BÁO CÁO TOÀN DIỆN & VẤN ĐÁP CHECKPOINT TUẦN 1
## DỰ ÁN LỚN: FRESHVEND — MÁY BÁN TRÁI CÂY TỰ ĐỘNG THÔNG MINH IOT
*(Phiên bản hoàn chỉnh & trọn vẹn nhất dành cho sinh viên)*

---

### 👤 THÔNG TIN ĐỊNH DANH
* **Học phần:** Lập trình Java (Mã học phần: `012012213602`)
* **Giảng viên hướng dẫn:** ThS. Nguyễn Văn Chiến
* **Sinh viên thực hiện:** **Lê Nguyễn Nhật Trường** (MSSV: `082206010882`)
* **Vai trò trong nhóm:** **Backend Developer (BE3)** & **Điều phối viên Phần cứng IoT**
* **Module phụ trách:** `FreshVend BE3 — IoT Telemetry API & Hardware Simulation`
* **Môi trường thực hành:** VS Code (Visual Studio Code), Java 17, Spring Boot 3.2.5, Thunder Client, Nền tảng Wokwi Simulator

---

# MỤC LỤC BÁO CÁO
1. **Phần I:** Kịch bản thuyết trình (2 phiên bản: Họp nhóm & Báo cáo với Giảng viên)
2. **Phần II:** Bảng đối chiếu nghiệm thu Checklist Tuần 1 (Khớp 100% file phân công)
3. **Phần III:** Đối chiếu quy chuẩn Đặc tả phần mềm (SRS Compliance)
4. **Phần IV:** Đối chiếu Sơ đồ phần cứng IoT & Luồng hoạt động hệ thống
5. **Phần V:** Giải phẫu chi tiết mã nguồn trong VS Code (`Fresh ven`)
6. **Phần VI:** Bộ 5 Test Case thực nghiệm trên Thunder Client (Kèm ảnh minh chứng)
7. **Phần VII:** Trọn bộ 8 câu hỏi vấn đáp "phao cứu sinh" chuẩn kỹ thuật
8. **Phần VIII:** Kế hoạch hành động tuần tiếp theo (Tuần 2)

---

# PHẦN I: KỊCH BẢN THUYẾT TRÌNH BẰNG LỜI

### 🗣️ Phiên bản 1: Trình bày họp nhóm (Khoảng 1 phút — Tự nhiên, thân thiện)
> *"Chào nhóm trưởng Hiếu và anh em nhóm FreshVend!  
> Tuần 1 này tui phụ trách phần **BE3 (IoT Telemetry API)** và **Điều phối mô phỏng IoT**. Tui xin báo cáo nhanh kết quả nghiệm thu như sau:
> 
> * **1. Về Backend:** Tui đã viết xong Server Spring Boot chạy cổng **8080**, mở API `POST /api/iot/telemetry`. API này đã thông luồng, sẵn sàng nhận dữ liệu nhiệt độ, độ ẩm từ ESP32 gửi lên.
> * **2. Về JSON & Bắt lỗi:** Tui và nhóm IoT đã chốt định dạng gồm 4 trường: `deviceId`, `temperature`, `humidity`, `timestamp`. Tui cài sẵn bắt lỗi chặt chẽ: gửi đúng thì trả về **200 OK**, nếu thiếu mã máy `deviceId` hoặc gửi nhiệt độ ảo (như 150°C vượt dải -40°C đến 80°C của cảm biến) thì Server sẽ chặn lại báo lỗi **400 Bad Request** bằng tiếng Việt rõ ràng, không lo sập server.
> * **3. Về Test & Minh chứng:** Tui dùng Thunder Client trong VS Code để test trực tiếp trên máy thật, tốc độ xử lý chỉ 6-7ms. Tui đã chụp đủ bộ ảnh minh chứng và lưu sẵn collection trong thư mục `postman/` để anh em kéo về test.
> * **4. Về Wokwi & Docker:** Tui đã dựng mạch Wokwi chạy thực tế (ESP32 + DHT22 + Servo SG90 quay 90° nhả khay), viết sẵn file `Dockerfile` và `README.md` hướng dẫn chi tiết.  
> Mọi tiêu chí Tuần 1 của tui đã hoàn thành 100%, sẵn sàng ghép nối cùng nhóm nha!"*

---

### 🎓 Phiên bản 2: Báo cáo với Giảng viên (Khoảng 2 phút — Trang trọng, chuyên nghiệp)
> *"Kính thưa Thầy Nguyễn Văn Chiến!  
> Em tên là Lê Nguyễn Nhật Trường, đảm nhiệm phân hệ **BE3 — IoT Telemetry API** và điều phối phần cứng IoT trong đồ án FreshVend. Em xin báo cáo kết quả nghiệm thu Tuần 1:
>
> 1. **Kiến trúc API:** Em đã triển khai RESTful Controller `IotTelemetryController` trên nền tảng Spring Boot 3, tiếp nhận gói tin JSON qua phương thức HTTP POST tại endpoint `/api/iot/telemetry`. Dữ liệu được ánh xạ tự động vào lớp DTO `TelemetryRequest`.
> 2. **Xử lý ngoại lệ & Nghiệp vụ chuỗi lạnh (SRS FR-IOT-12):** Hệ thống áp dụng cơ chế xác thực đầu vào nghiêm ngặt: kiểm tra tính toàn vẹn của mã định danh `deviceId`, kiểm tra giới hạn vật lý của cảm biến DHT22 trong dải (-40°C đến 80°C) và độ ẩm (0% đến 100%). Mọi dữ liệu không hợp lệ đều bị từ chối với mã phản hồi HTTP 400 Bad Request kèm thông điệp nghiệp vụ cụ thể.
> 3. **Thực nghiệm & Kiểm thử:** Em đã kiểm thử tự động bằng Thunder Client với đầy đủ các bộ dữ liệu biên (boundary tests) và dữ liệu thiếu (missing fields), độ trễ trung bình đạt 6.5ms.
> 4. **Mô phỏng phần cứng IoT:** Em đã thiết lập hoàn chỉnh mạch mô phỏng trên nền tảng Wokwi gồm ESP32, DHT22 và Servo nhả sản phẩm, đảm bảo gói tin JSON xuất ra khớp 100% với hợp đồng API của Backend. Đồng thời em đã chuẩn bị sẵn file `Dockerfile` đóng gói container và tài liệu `README.md` theo chuẩn công nghiệp ạ."*

---

# PHẦN II: BẢNG ĐỐI CHIẾU CHECKLIST NGHIỆM THU TUẦN 1
*(Căn cứ theo tài liệu phân công chính thức `FreshVend_Phan_Cong_Tuan_1.docx`)*

| STT | Nhiệm vụ phân công cho Nhật Trường (BE3) | File mã nguồn thực tế trong VS Code | Minh chứng thực tế đã chạy | Đánh giá |
| :-: | :--- | :--- | :--- | :-: |
| **1** | **Tạo API mẫu `POST /api/iot/telemetry`** | `src/main/java/com/freshvend/controller/IotTelemetryController.java` | Spring Boot chạy cổng 8080, log Terminal in nhận dữ liệu | **ĐẠT (100%)** |
| **2** | **Thống nhất JSON với nhóm IoT** | `src/main/java/com/freshvend/dto/TelemetryRequest.java` | 4 trường (`deviceId`, `temperature`, `humidity`, `timestamp`) khớp với Wokwi | **ĐẠT (100%)** |
| **3** | **Kiểm tra dữ liệu Đúng, Thiếu hoặc Sai** | Code Validation trong `IotTelemetryController.java` | - Test thiếu `deviceId` -> 400 Bad Request<br>- Test 150°C -> 400 Bad Request | **ĐẠT (100%)** |
| **4** | **Có request Postman / Thunder Client** | File `postman/FreshVend_BE3_Collection.json` | Đã lưu sẵn 5 kịch bản test trong Thunder Client | **ĐẠT (100%)** |
| **5** | **Spring Boot chạy được & có README hướng dẫn** | File `README.md` (tại thư mục gốc dự án) | Tài liệu 7 mục hoàn chỉnh: kiến trúc, cách chạy, API contract, test cases | **ĐẠT (100%)** |
| **6** | **Wokwi mô phỏng ESP32 + DHT22 + Servo** | File C++ Arduino (`sketch.ino`) trên Wokwi | Ảnh 4: Mạch chạy thật (00:34s), Serial Monitor xuất JSON và điều khiển Servo | **ĐẠT (100%)** |
| **7** | **Phối hợp thử API khi Spring Boot chạy Docker** | File `Dockerfile` (tại thư mục gốc dự án) | Cấu hình base image `eclipse-temurin:17-jdk-alpine`, EXPOSE 8080 | **ĐẠT (100%)** |

---

# PHẦN III: ĐỐI CHIẾU QUY CHUẨN ĐẶC TẢ PHẦN MỀM (SRS COMPLIANCE)
*(Khớp với tài liệu `Đặc tả đề tài nhóm.docx`)*

* **FR-IOT-01 (Receive Sensor Data):** Endpoint `POST /api/iot/telemetry` tiếp nhận đầy đủ dữ liệu môi trường từ ESP32.
* **FR-IOT-07 & BR-IOT-06 (Cold Chain Monitoring):** Phục vụ giám sát ngưỡng nhiệt độ chuỗi lạnh từ **2.0°C đến 8.0°C** bảo quản trái cây tươi.
* **FR-IOT-10 & BR-IOT-08 (Dispensing Servo Control):** Mô phỏng Wokwi đã kích hoạt Servo quay 90° để mở chốt nhả khay trái cây và đóng chốt về 0°.
* **FR-IOT-12 (Handle Invalid Sensor Data):** Hệ thống chủ động loại bỏ dữ liệu thiếu hoặc sai dải vật lý, không để dữ liệu lỗi ảnh hưởng đến cảnh báo.
* **Non-Functional Requirement (Performance):** Thời gian phản hồi thực tế đạt **6 - 7ms**, vượt xa tiêu chuẩn SRS yêu cầu (< 500ms).

---

# PHẦN IV: ĐỐI CHIẾU SƠ ĐỒ PHẦN CỨNG IOT & LUỒNG HOẠT ĐỘNG
*(Khớp với ảnh sơ đồ `1791912062394_...jpg`)*

### 1. Bảng kết nối chân (Pinout Mapping):
* **Cảm biến DHT22 (Chân Data):** Nối vào **GPIO 4** của ESP32 (đo nhiệt độ, độ ẩm).
* **Động cơ Servo SG90 (Chân Signal PWM):** Nối vào **GPIO 13** (hoặc GPIO 18) của ESP32.
* **Nguồn cấp:** Nguồn 5V ngoài cấp riêng cho Servo, **nối chung chân GND** giữa ESP32 và nguồn ngoài để đồng bộ mức logic 3.3V.

### 2. Danh mục linh kiện & Dự trù kinh phí:
* ESP32 DevKit V1: 80.000đ – 155.000đ (1 chiếc)
* Cảm biến DHT22 (AM2302): 40.000đ – 80.000đ (1 chiếc)
* Động cơ Servo SG90: 25.000đ – 50.000đ (1 chiếc)
* Breadboard 830 điểm + Dây cắm Dupont: 30.000đ – 70.000đ
* Cáp nạp USB + Củ nguồn 5V 2A: 60.000đ – 120.000đ  
👉 **Tổng chi phí phần cứng dự trù:** **~300.000đ – 650.000đ** (rất tiết kiệm và khả thi).

---

# PHẦN V: GIẢI PHẪU CHI TIẾT MÃ NGUỒN TRONG VS CODE (`Fresh ven`)

1. **`pom.xml`:**  
   Khai báo Spring Boot version `3.2.5`, Java 17, tích hợp `spring-boot-starter-web` (chạy ngầm máy chủ Tomcat cổng 8080) và `spring-boot-starter-test`.
2. **`FreshVendApplication.java`:**  
   Lớp chứa hàm `main`, kích hoạt toàn bộ cơ chế tự động cấu hình (Auto-configuration) và Dependency Injection của Spring Boot.
3. **`TelemetryRequest.java` (Lớp DTO):**  
   Chứa 4 thuộc tính cốt lõi:
   - `private String deviceId;`
   - `private Double temperature;`
   - `private Double humidity;`
   - `private String timestamp;`
4. **`ApiResponse.java` (Lớp Phản hồi chuẩn):**  
   Đóng gói kết quả trả về gồm 3 trường: `status` ("SUCCESS" / "ERROR"), `message` (nội dung thông báo), `serverTime` (thời điểm hệ thống xử lý xong).
5. **`IotTelemetryController.java` (Lớp Điều khiển API):**  
   - Kiểm tra `request == null` -> Lỗi 400.
   - Kiểm tra `deviceId == null || trim().isEmpty()` -> Lỗi 400.
   - Kiểm tra `temperature == null` -> Lỗi 400.
   - Kiểm tra `temperature < -40.0 || temperature > 80.0` -> Lỗi 400.
   - Kiểm tra `humidity < 0.0 || humidity > 100.0` -> Lỗi 400.
   - Nếu hợp lệ: In log ra Terminal và trả về mã `200 OK`.

---

# PHẦN VI: BỘ 5 KỊCH BẢN TEST TRÊN THUNDER CLIENT

| Test Case | Method & Endpoint | Payload JSON | Kết quả trả về | Ý nghĩa thực tế |
| :--- | :--- | :--- | :---: | :--- |
| **Case 1: Dữ liệu chuẩn** | `POST /api/iot/telemetry` | `{"deviceId":"ESP32_01","temperature":5.5,"humidity":65.0,"timestamp":"2026-10-19T00:00:00Z"}` | **200 OK** (6ms) | Dữ liệu hợp lệ, nhiệt độ thuộc chuỗi lạnh an toàn (2-8°C). |
| **Case 2: Thiếu deviceId** | `POST /api/iot/telemetry` | `{"temperature":15.5,"humidity":65.0,"timestamp":"2026-10-19T00:55:00Z"}` | **400 Bad Request** | Bắt lỗi thiếu ID, bảo vệ hệ thống không bị dữ liệu vô danh. *(Minh chứng Ảnh 1)* |
| **Case 3: Nhiệt độ ngoài dải đo** | `POST /api/iot/telemetry` | `{"deviceId":"ESP32_01","temperature":150.0,"humidity":65.0,"timestamp":"2026-10-19T00:55:00Z"}` | **400 Bad Request** | Bắt lỗi cảm biến ảo, tránh kích hoạt báo cháy/quá nhiệt giả. *(Minh chứng Ảnh 2)* |
| **Case 4: Thiếu nhiệt độ** | `POST /api/iot/telemetry` | `{"deviceId":"ESP32_01","humidity":65.0,"timestamp":"2026-10-19T00:00:00Z"}` | **400 Bad Request** | Bắt lỗi thiếu số đo nhiệt độ cốt lõi. |
| **Case 5: Độ ẩm không hợp lệ** | `POST /api/iot/telemetry` | `{"deviceId":"ESP32_01","temperature":5.5,"humidity":120.0,"timestamp":"2026-10-19T00:55:00Z"}` | **400 Bad Request** | Bắt lỗi độ ẩm vượt 100%. |

---

# PHẦN VII: TRỌN BỘ 8 CÂU HỎI VẤN ĐÁP "PHÒNG THÂN"

#### ❓ Câu 1 (FE2 - Lương Thành Công): *"Dashboard của tui cần hiển thị nhiệt độ thì lấy ở đâu?"*
> **Trả lời:** *"Tuần 1 tui ưu tiên API POST nhận dữ liệu cảm biến trước. Sang Tuần 2-3 khi kết nối cơ sở dữ liệu với bạn Văn Hiếu (BE2), tui sẽ mở thêm endpoint `GET /api/iot/latest` và `GET /api/iot/history` để trả về số liệu mới nhất cho ông vẽ biểu đồ Chart.js lên Dashboard nha."*

#### ❓ Câu 2 (BE2 - Văn Hiếu): *"Các trường dữ liệu cảm biến gồm những gì để tui tạo bảng PostgreSQL?"*
> **Trả lời:** *"Bảng `telemetry_logs` của ông tạo các cột này là chuẩn nè: `device_id` (VARCHAR), `temperature` (DOUBLE), `humidity` (DOUBLE), `recorded_at` (TIMESTAMP) và `is_alert` (BOOLEAN)."*

#### ❓ Câu 3 (BE1 - Xuân Hiếu): *"Code Spring Boot đã sẵn sàng đóng gói Docker chưa?"*
> **Trả lời:** *"Tui đã viết sẵn file `Dockerfile` chuẩn OpenJDK 17 Alpine ngay thư mục gốc rồi. Khi ông gộp `docker-compose.yml`, chỉ cần trỏ build vào Dockerfile này và map cổng `8080:8080` là chạy ngay."*

#### ❓ Câu 4 (Nhóm hỏi): *"Tình hình linh kiện IoT khi nào mua và giá bao nhiêu?"*
> **Trả lời:** *"Theo kế hoạch nhóm đã rà soát: gồm ESP32, DHT22, Servo SG90, Breadboard, dây cắm và củ nguồn 5V riêng, tổng chi phí khoảng 300.000đ - 650.000đ. Tuần 1 mình chốt code trên Wokwi, sang Tuần 2 nhận linh kiện là ráp test được ngay."*

#### ❓ Câu 5 (Thầy cô hỏi): *"Tại sao Servo lại phải dùng nguồn 5V riêng mà không cắm vào ESP32?"*
> **Trả lời:** *"Dạ thưa Thầy, động cơ Servo khi quay cần dòng khởi động lớn (500mA - 1A), chân cấp nguồn của ESP32 không đáp ứng đủ. Nếu cắm chung sẽ gây sụt áp làm reset ESP32 hoặc hỏng vi điều khiển. Do đó phải dùng nguồn 5V ngoài cho Servo và nối chung chân GND với ESP32 để đồng bộ tín hiệu ạ."*

#### ❓ Câu 6 (Thầy cô hỏi): *"Tại sao phân công ghi Postman mà em lại dùng Thunder Client?"*
> **Trả lời:** *"Dạ cả Postman và Thunder Client đều là công cụ gửi HTTP Request để test API. Em chọn Thunder Client vì nó tích hợp trực tiếp ngay trong VS Code rất nhẹ máy, test tức thì. Em vẫn xuất sẵn file collection JSON chuẩn để các bạn dùng Postman import vào kiểm thử bình thường ạ."*

#### ❓ Câu 7 (Thầy cô hỏi): *"Tại sao trường `deviceId` bắt buộc phải có?"*
> **Trả lời:** *"Dạ vì hệ thống FreshVend quản lý nhiều máy bán hàng khác nhau. Nếu gói tin gửi về không có `deviceId` thì Server không thể biết nhiệt độ này là của máy nào để ghi nhận hay phát cảnh báo cho nhân viên vận hành máy đó ạ."*

#### ❓ Câu 8 (Thầy cô hỏi): *"Kế hoạch Tuần 2 của phân hệ BE3 là gì?"*
> **Trả lời:** *"Dạ sang Tuần 2, em sẽ nạp code từ Wokwi sang chip ESP32 thật để thử nghiệm đường truyền thật qua Wi-Fi, đồng thời phối hợp với BE2 kết nối Spring Data JPA để lưu dữ liệu vào cơ sở dữ liệu PostgreSQL thay vì chỉ in ra console ạ."*

---

# PHẦN VIII: KẾ HOẠCH HÀNH ĐỘNG TUẦN 2 (TIẾP NỐI CHECKPOINT)
1. Nhận linh kiện phần cứng (ESP32, DHT22, Servo SG90) và thử nghiệm mạch thực tế.
2. Nạp firmware Arduino C++ từ Wokwi sang ESP32 thật qua cổng USB Serial.
3. Ghép nối module Backend BE3 với Database PostgreSQL (tạo Entity `TelemetryLog` và Repository).
4. Mở rộng thêm endpoint `GET /api/iot/history` hỗ trợ Dashboard của Frontend.
