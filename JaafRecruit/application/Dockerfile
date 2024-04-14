FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/ApplicationService.jar

EXPOSE 4446

ENTRYPOINT ["java", "-jar", "/app/ApplicationService.jar"]