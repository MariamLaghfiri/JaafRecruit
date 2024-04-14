FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/GatewayService.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/GatewayService.jar"]