FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY target/lab4player-0.0.1-SNAPSHOT.jar player.jar
EXPOSE 8081
CMD ["java", "-jar", "player.jar"]