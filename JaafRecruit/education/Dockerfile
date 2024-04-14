FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/EducationService.jar

EXPOSE 4442

ENTRYPOINT ["java", "-jar", "/app/EducationService.jar"]