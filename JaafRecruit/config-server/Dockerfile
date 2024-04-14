FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/ConfigServer.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/app/ConfigServer.jar"]