FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/JobPostingService.jar

EXPOSE 4447

ENTRYPOINT ["java", "-jar", "/app/JobPostingService.jar"]