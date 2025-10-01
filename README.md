# Demo Spring Cloud Gateway

## Cấu trúc dự án

```
demo-spring-microservices-2/
├── gateway/                    # Dịch vụ API Gateway
├── student-service/           # Microservice quản lý sinh viên
├── assignment-service/        # Microservice quản lý bài tập
├── docker-compose.yml         # File điều phối Docker
├── build.gradle.kts           # Cấu hình build gốc
└── settings.gradle.kts        # Thiết lập đa module
```

## Công nghệ sử dụng

- **Spring Boot**: 3.5.6
- **Spring Cloud Gateway**: 2025.0.0
- **Java**: 21
- **Công cụ build**: Gradle (Kotlin DSL)
- **Container**: Docker

## Hướng dẫn Build & Chạy

### 1. Build tất cả dịch vụ

```bash
./gradlew clean build
```

### 2. Chạy bằng Docker Compose

```bash
docker compose up --build
```

### 3. Kiểm thử các endpoint

#### Thông qua Gateway

| Endpoint                               | Mô tả               |
|----------------------------------------|---------------------|
| http://localhost:8080/students/info    | Thông tin sinh viên |
| http://localhost:8080/students/list    | Danh sách sinh viên |
| http://localhost:8080/assignments/info | Thông tin bài tập   |
| http://localhost:8080/assignments/list | Danh sách bài tập   |

### Truy cập trực tiếp (nếu cần)

| Endpoint                   | Mô tả             |
|----------------------------|-------------------|
| http://localhost:8081/info | Dịch vụ sinh viên |
| http://localhost:8082/info | Dịch vụ bài tập   |

### 4. Sử dụng curl

```bash
# Kiểm thử dịch vụ sinh viên qua gateway
curl http://localhost:8080/students/info

# Kiểm thử dịch vụ bài tập qua gateway
curl http://localhost:8080/assignments/list
```

## Cấu hình Gateway

Gateway được cấu hình định tuyến:

- `/students/**` → `student-service:8081`
- `/assignments/**` → `assignment-service:8082`

Bộ lọc `StripPrefix=1` sẽ loại bỏ phần đầu tiên của đường dẫn trước khi chuyển tiếp.

## Phát triển

### Chạy dịch vụ cục bộ (không dùng Docker)

Terminal 1 - Gateway:

```bash
./gradlew :gateway:bootRun
```

Terminal 2 - Student Service:

```bash
./gradlew :student-service:bootRun
```

Terminal 3 - Assignment Service:

```bash
./gradlew :assignment-service:bootRun
```

**Lưu ý**: Khi chạy cục bộ, hãy cập nhật `gateway/src/main/resources/application.yml` để sử dụng `localhost` thay vì tên
dịch vụ:

```yaml
uri: http://localhost:8081  # cho student-service
uri: http://localhost:8082  # cho assignment-service
```

## Giám sát

Các endpoint actuator của Gateway có tại:

- Health: http://localhost:8080/actuator/health

## Dừng dịch vụ

```bash
docker compose down
```

## Khắc phục sự cố

1. **Xung đột cổng**: Đảm bảo các cổng 8080, 8081, 8082 đang rảnh
2. **Lỗi build**: Chạy `./gradlew clean` trước
3. **Lỗi Docker**: Kiểm tra Docker daemon đã chạy
4. **Gateway không truy cập được dịch vụ**: Đảm bảo tất cả container cùng một mạng
