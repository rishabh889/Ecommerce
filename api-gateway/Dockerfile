# API Gateway Dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/api-gateway-*.jar app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]
