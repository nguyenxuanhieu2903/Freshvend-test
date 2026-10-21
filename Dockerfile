# ==============================================================================
# FreshVend BE3 - IoT Telemetry API Dockerfile (Multi-Stage Production Build)
# ==============================================================================

# STAGE 1: Build & Package ứng dụng bằng Maven
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /build

# Tận dụng Docker Cache cho dependencies
COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

# Copy mã nguồn và đóng gói JAR
COPY src ./src
RUN mvn clean package -DskipTests -B

# STAGE 2: Runtime Image tối ưu hóa dung lượng & bảo mật
FROM eclipse-temurin:17-jre-alpine AS runner
WORKDIR /app

# Thiết lập múi giờ Việt Nam cho log cảm biến
ENV TZ=Asia/Ho_Chi_Minh

# Tạo non-root user để tăng cường bảo mật container
RUN addgroup -S freshvend && adduser -S freshvend -G freshvend

# Lấy file jar đã build từ Stage 1
COPY --from=builder /build/target/*.jar /app/app.jar
RUN chown -R freshvend:freshvend /app

USER freshvend:freshvend

# Mở cổng API
EXPOSE 8080

# Tối ưu hóa JVM cho môi trường Container (Tránh tràn RAM)
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh", "-c", "java  -jar /app/app.jar"]
