FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY target/lab4gateway-0.0.1-SNAPSHOT.jar gateway.jar
EXPOSE 8080
CMD ["java", "-jar", "gateway.jar"]