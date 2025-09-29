FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/clients-2.1.0-SNAPSHOT.jar app.jar

EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]
