# ==============================================================================
# FreshVend Spring Boot Application - Production Multi-Stage Dockerfile
# Base: Eclipse Temurin OpenJDK 21 (LTS)
# ==============================================================================

# ------------------------------------------------------------------------------
# Stage 1: Build & Package (Maven + JDK 21)
# ------------------------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /build

# 1. Cache Maven dependencies layer
COPY backend/pom.xml ./backend/pom.xml
RUN cd backend && mvn dependency:go-offline -B

# 2. Copy frontend assets/templates and backend source code
COPY frontend ./frontend
COPY backend ./backend

# 3. Build executable JAR (pom.xml automatically bundles frontend/templates & static)
RUN cd backend && mvn clean package -DskipTests -B

# ------------------------------------------------------------------------------
# Stage 2: Production Runtime (Lightweight JRE 21)
# ------------------------------------------------------------------------------
FROM eclipse-temurin:21-jre-alpine AS runner

WORKDIR /app

# Set timezone
ENV TZ=Asia/Ho_Chi_Minh

# Create non-root system user for production security
RUN addgroup -S freshvend && adduser -S freshvend -G freshvend

# Copy generated JAR from builder stage
COPY --from=builder /build/backend/target/*.jar /app/app.jar

# Set ownership to unprivileged user
RUN chown -R freshvend:freshvend /app

# Run as non-root user
USER freshvend:freshvend

# Expose Spring Boot web port
EXPOSE 8080

# JVM container memory optimizations for Java 21
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

# Launch application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
