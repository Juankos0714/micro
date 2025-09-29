FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/catalog-2.1.0-SNAPSHOT.jar app.jar

EXPOSE 9002

ENTRYPOINT ["java", "-jar", "app.jar"]
