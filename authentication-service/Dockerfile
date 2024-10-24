# Authentication Service Dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/authentication-service-*.jar app.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "app.jar"]
