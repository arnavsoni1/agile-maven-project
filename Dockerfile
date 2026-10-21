FROM eclipse-temurin:8-jre

WORKDIR /app

COPY target/simple-maven-app-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
