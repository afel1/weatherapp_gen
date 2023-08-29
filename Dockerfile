FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/weatherapp-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "weatherapp-0.0.1-SNAPSHOT.jar"]