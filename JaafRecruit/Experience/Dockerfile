FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/ExperienceService.jar

EXPOSE 4443

ENTRYPOINT ["java", "-jar", "/app/ExperienceService.jar"]