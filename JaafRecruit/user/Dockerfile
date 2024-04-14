FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/UserService.jar

EXPOSE 4449

ENTRYPOINT ["java", "-jar", "/app/UserService.jar"]