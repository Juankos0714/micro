FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/sales-2.1.0-SNAPSHOT.jar app.jar

EXPOSE 9003

ENTRYPOINT ["java", "-jar", "app.jar"]
