FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/DiscoveryService.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/DiscoveryService.jar"]