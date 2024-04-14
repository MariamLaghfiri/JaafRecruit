FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/JobSeekerService.jar

EXPOSE 4448

ENTRYPOINT ["java", "-jar", "/app/JobSeekerService.jar"]