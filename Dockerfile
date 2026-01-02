# Build stage: compila con Maven (OpenJDK 8)
FROM maven:3-openjdk-8-slim AS builder
WORKDIR /workspace

# Copiar POMs para aprovechar la cache de dependencias
COPY pom.xml ./
COPY */pom.xml ./

# Descargar dependencias (sin compilar código)
#RUN mvn -B -ntp dependency:go-offline

# Copiar el código fuente completo
COPY . .

# Módulo de la aplicación principal (cambiar si hace falta)
ARG APP_MODULE=customers-app
ARG MAVEN_ARGS="-DskipTests"

# Compilar el módulo principal y sus dependencias
RUN mvn -B -ntp -pl ${APP_MODULE} -am clean package ${MAVEN_ARGS}

# Runtime stage: imagen ligera con JRE 8
#FROM openjdk:8-jre-slim
#FROM amazoncorretto:17-alpine-jdk
FROM eclipse-temurin:8-jre-focal
WORKDIR /app

ARG APP_MODULE=customers-app
ARG JAR_NAME=app.jar

# Copiar el JAR construido desde el stage 'builder'
COPY --from=builder /workspace/${APP_MODULE}/target/*.jar ${JAR_NAME}

# Puerto expuesto por la aplicación (ajustar si es necesario)
EXPOSE 8081:8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

#docker build -t customers/my-app:1.0.0 .
#docker run -p 8081:8080 customers/my-app:1.0.0