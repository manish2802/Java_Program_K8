FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/*.jar Javaprogram.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Javaprogram.jar"]