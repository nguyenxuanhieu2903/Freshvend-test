# 🍏 FreshVend - Hệ thống Máy Bán Trái Cây Tự Động Ứng Dụng IoT

> **Dự án Môn học**: Lập Trình Java (Mã HP: 012012213602) - UTH  
> **Nhóm trưởng / Tech Lead**: Nguyễn Xuân Hiếu (BE1)  
> **Kiến trúc**: Spring Boot Monolith + Thymeleaf + PostgreSQL + ESP32 IoT  
> **Môi trường chạy**: Java 21 (LTS), Spring Boot 3.3.4, Docker Compose  

---

## 👥 1. Phân Công Vai Trò & Trách Nhiệm 6 Thành Viên (Tuần 1 - 6)

| STT | Thành viên | Vai trò | Trách nhiệm chính | Tiền tố nhánh gợi ý |
|:---:|:---|:---|:---|:---|
| 1 | **Nguyễn Xuân Hiếu** | **BE1 / Leader** | Core Spring Boot architecture, Docker Compose, API Contracts, trạng thái đơn hàng & thanh toán | `feature/be1-core-...` |
| 2 | **Văn Hiếu** | **BE2 / Database** | Thiết kế ERD, PostgreSQL schema, JPA Entities, Database Migration, Data Seeding | `feature/be2-db-...` |
| 3 | **Nhật Trường** | **BE3 / IoT API** | Xây dựng REST API tiếp nhận Telemetry (`POST /api/iot/telemetry`), tích hợp Wokwi / ESP32 | `feature/be3-iot-...` |
| 4 | **Quốc Vinh** | **FE1 / Client Site** | Giao diện Thymeleaf khách hàng (`frontend/templates/customer/`, đặt hàng, giỏ hàng) | `feature/fe1-customer-...` |
| 5 | **Lương Thành Công** | **FE2 / Operator** | Web Dashboard vận hành (`frontend/templates/operator/`, cảnh báo nhiệt độ, tồn kho) | `feature/fe2-operator-...` |
| 6 | **Duy Hoàng** | **FE3 / Admin & UI** | Shared Layout (`frontend/templates/fragments/`), Login, Quản trị Admin CRUD (`admin/`) | `feature/fe3-admin-...` |

---

## 🛠️ 2. Yêu Cầu Môi Trường Cài Đặt (Prerequisites)

Tất cả thành viên cần cài đặt trước:
- **Git**: [Tải Git SCM](https://git-scm.com/)
- **Docker Desktop**: Đã bật và đang chạy (Hỗ trợ Docker Compose v2+).
- **JDK 21 (LTS)**: Khuyến nghị Oracle JDK 21 hoặc Eclipse Temurin 21.
- **IDE**: IntelliJ IDEA (khuyên dùng) hoặc VS Code (cài extension Java Extension Pack, Lombok Annotations).

---

## 🚀 3. Hướng Dẫn Khởi Chạy Dự Án (Quick Start)

### Bước 1: Clone Repository
```bash
git clone <URL_REPO_GITHUB>
cd Project
```

### Bước 2: Chạy toàn bộ hệ thống bằng Docker Compose (Khuyên dùng)
Hệ thống sẽ tự động build image Spring Boot đa tầng (multi-stage Java 21), tự động đóng gói toàn bộ thư mục `frontend/` và `backend/`, đồng thời khởi chạy PostgreSQL 15:
```bash
# Khởi động dịch vụ ở chế độ chạy nền (-d) và tự động build
docker compose up -d --build
```

- **Kiểm tra trạng thái containers**:
  ```bash
  docker compose ps
  ```
- **Xem log hệ thống thời gian thực**:
  ```bash
  docker compose logs -f app
  ```
- **Dừng hệ thống**:
  ```bash
  docker compose down
  ```
  *(Dữ liệu Database được lưu an toàn trong volume `freshvend_postgres_data`)*.

### Bước 3: Truy cập hệ thống
- **Ứng dụng Web / API**: [http://localhost:8080](http://localhost:8080)
- **PostgreSQL Database**:
  - **Host**: `localhost` (khi kết nối từ máy ngoài) hoặc `postgres` (trong mạng Docker)
  - **Port**: `5432`
  - **Database Name**: `freshvend_db`
  - **Username**: `postgres`
  - **Password**: `postgrespassword`

---

## 💻 4. Chế Độ Lập Trình Nhanh (Local Development Mode)

Dành cho các thành viên Backend & Frontend cần sửa code và test nóng qua Spring Boot DevTools:

1. **Chỉ bật riêng Database PostgreSQL qua Docker**:
   ```bash
   docker compose up -d postgres
   ```
2. **Chạy ứng dụng Spring Boot trực tiếp từ máy host**:
   ```bash
   cd backend
   # Windows (Powershell / CMD):
   .\mvnw.cmd spring-boot:run

   # macOS / Linux:
   ./mvnw spring-boot:run
   ```
   *(File cấu hình `application.yml` đã được thiết lập mặc định trỏ về `localhost:5432` khi chạy ngoài container).*

---

## 🌿 5. Quy Tắc Quản Lý Nhánh Git (Git Branching & Workflow)

Toàn bộ nhóm bắt buộc tuân theo quy trình làm việc chuẩn sau:

```
main (Production / Nộp bài cuối kỳ)
  └── develop (Nhánh tích hợp tính năng tuần / Sprint)
        ├── feature/be2-init-entities
        ├── feature/be3-iot-telemetry-api
        ├── feature/fe1-customer-catalog
        ├── feature/fe2-operator-dashboard
        └── feature/fe3-admin-layout
```

### 1. Quy tắc các nhánh chính:
- **`main`**: Nhánh bảo vệ, chỉ chứa mã nguồn đã kiểm thử hoàn chỉnh vào cuối tuần/cuối kỳ. **TUYỆT ĐỐI KHÔNG commit trực tiếp lên `main`**.
- **`develop`**: Nhánh trung tâm để tích hợp code tuần 1, 2, ... Chỉ merge code vào `develop` thông qua **Pull Request (PR)** đã được Leader (Xuân Hiếu) duyệt.

### 2. Quy tắc đặt tên nhánh tính năng (`feature/*`):
Tạo nhánh mới từ `develop` theo cú pháp:
`feature/<vai-trò>-<tên-chức-năng-ngắn-gọn>`

*Ví dụ:*
- `feature/be2-schema-and-entities` (Văn Hiếu - BE2)
- `feature/be3-telemetry-endpoint` (Nhật Trường - BE3)
- `feature/fe1-product-list-page` (Quốc Vinh - FE1)
- `feature/fe2-temperature-monitor` (Thành Công - FE2)
- `feature/fe3-base-layout-fragments` (Duy Hoàng - FE3)
- `bugfix/fix-inventory-deduction` (Khi sửa lỗi phát sinh)

### 3. Quy chuẩn Commit Message (Conventional Commits):
Bắt buộc dùng tiền tố rõ ràng:
- `feat:` Thêm chức năng mới (vd: `feat(api): create telemetry controller`)
- `fix:` Sửa lỗi (vd: `fix(order): prevent negative inventory on dispense`)
- `docs:` Cập nhật tài liệu (vd: `docs: update API contracts for IoT`)
- `style:` Chỉnh sửa format, CSS, giao diện không đổi logic
- `refactor:` Tái cấu trúc code mà không đổi tính năng

### 4. Quy trình đẩy code lên GitHub (Standard PR Flow):
```bash
# 1. Chuyển sang develop và cập nhật code mới nhất từ nhóm
git checkout develop
git pull origin develop

# 2. Tạo nhánh feature của riêng bạn
git checkout -b feature/be2-init-entities

# 3. Code, test cẩn thận trên máy cá nhân và commit
git add .
git commit -m "feat(database): implement Product and Inventory JPA entities"

# 4. Đẩy nhánh lên GitHub và tạo Pull Request (PR) vào nhánh 'develop'
git push origin feature/be2-init-entities
```
> ⚠️ **Lưu ý**: Sau khi tạo PR, gửi link lên nhóm Zalo/Discord để Leader Xuân Hiếu review và merge. Không tự ý merge PR của chính mình!

---

## 📁 6. Cấu Trúc Thư Mục Phân Tách Frontend & Backend (Theo Yêu Cầu Giảng Viên)

Dự án tách bạch rõ ràng 2 thư mục **`frontend/`** và **`backend/`** ở thư mục gốc:

```
Project/
├── .gitignore                      # Git ignore chung toàn dự án
├── docker-compose.yml              # Cấu hình container PostgreSQL & Spring Boot App
├── Dockerfile                      # Multi-stage build Java 21 (đóng gói cả frontend & backend)
├── README.md                       # Hướng dẫn dự án cho thành viên
├── PROJECT_RULES.md                # Quy tắc nghiệp vụ & lập trình cốt lõi
├── FreshVend_SRS.md                # Tài liệu đặc tả yêu cầu chi tiết (SRS)
│
├── frontend/                       # Thư mục giao diện độc lập (Dành riêng cho FE1, FE2, FE3)
│   ├── static/                     # Tài nguyên tĩnh
│   │   ├── css/                    # File CSS giao diện
│   │   ├── js/                     # File Javascript xử lý client
│   │   └── images/                 # Hình ảnh sản phẩm, logo
│   └── templates/                  # Thư mục chứa toàn bộ màn hình Thymeleaf (.html)
│       ├── customer/               # Giao diện khách mua hàng (Quốc Vinh FE1)
│       │   └── index.html
│       ├── operator/               # Giao diện giám sát vận hành (Thành Công FE2)
│       ├── admin/                  # Giao diện quản trị Admin (Duy Hoàng FE3)
│       └── fragments/              # Header, footer, sidebar dùng chung (Duy Hoàng FE3)
│
└── backend/                        # Thư mục mã nguồn Spring Boot Monolith (Java 21)
    ├── pom.xml                     # Cấu hình Maven (Tự động map frontend/ vào target/classes)
    ├── mvnw / mvnw.cmd             # Maven Wrapper
    └── src/
        ├── main/
        │   ├── java/com/freshvend/
        │   │   ├── BackendApplication.java
        │   │   ├── config/         # Cấu hình Security, MVC, Bean
        │   │   ├── controller/     # Web & REST Controllers
        │   │   ├── dto/            # Data Transfer Objects (Request/Response)
        │   │   ├── entity/         # JPA Entities ánh xạ Database
        │   │   ├── repository/     # Spring Data JPA Repositories
        │   │   └── service/        # Business Logic & Transactions
        │   └── resources/
        │       └── application.yml # Cấu hình DB, JPA, Thymeleaf, Logging
        └── test/                   # Unit test & Integration test
```

> 💡 **Cơ chế hoạt động**:
> - Nhóm Frontend (FE1, FE2, FE3) làm việc **100% trong thư mục `frontend/`**.
> - File `backend/pom.xml` đã được cấu hình tự động ánh xạ thư mục `frontend/templates` và `frontend/static` vào Classpath khi build.
> - File `backend/src/main/resources/application.yml` hỗ trợ nạp trực tiếp static resources từ `frontend/static/` giúp thay đổi CSS/JS ăn ngay lập tức khi chạy local.

---

## 📌 7. Lưu Ý Cốt Lõi Cho Cả Nhóm (Trích PROJECT_RULES.md)

1. **Tổng tiền đơn hàng (`totalAmount`)**: Phải tính toán 100% tại Backend Service. Cấm tin tưởng số tiền gửi lên từ Frontend.
2. **Tồn kho (`stockQuantity`)**: Phải nằm trong Database Transaction (`@Transactional`), không bao giờ để tồn kho âm (`>= 0`). Chỉ trừ kho khi có xác nhận nhả hàng thực tế thành công từ máy.
3. **Trạng thái đơn hàng và thanh toán độc lập**: `Payment Status: SUCCESS` chưa đồng nghĩa với `Order Status: COMPLETED`.
4. **Dữ liệu IoT**: Mọi bản tin telemetry gửi lên bắt buộc có `timestamp` và trường phân định `sourceType` (`REAL` hoặc `SIMULATED`).
