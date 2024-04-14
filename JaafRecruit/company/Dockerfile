FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/CompanyService.jar

EXPOSE 4445

ENTRYPOINT ["java", "-jar", "/app/CompanyService.jar"]