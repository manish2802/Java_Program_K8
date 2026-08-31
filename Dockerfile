FROM eclipse-temurin:17-jre

WORKDIR /app

# Spring Boot application
COPY target/*.jar KeyClockApp.jar

# Download OpenTelemetry Java Agent
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar

EXPOSE 8080

ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar","KeyClockApp.jar"]