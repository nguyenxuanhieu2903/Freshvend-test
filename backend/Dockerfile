# ==============================================================================
# FreshVend Spring Boot Application - Dockerfile (Backend Scope)
# Base: Eclipse Temurin OpenJDK 21 (LTS)
# ==============================================================================

FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /build

COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:21-jre-alpine AS runner

WORKDIR /app

ENV TZ=Asia/Ho_Chi_Minh

RUN addgroup -S freshvend && adduser -S freshvend -G freshvend

COPY --from=builder /build/target/*.jar /app/app.jar

RUN chown -R freshvend:freshvend /app

USER freshvend:freshvend

EXPOSE 8080

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
