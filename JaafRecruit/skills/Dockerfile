FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /app

# Copy any jar file from the target directory into the container at /app
# Here, the jar is renamed to UtilisateursService.jar for clarity
COPY target/*.jar /app/SkillsService.jar

# Make port 4441 available to the world outside this container
EXPOSE 4441

# Run the jar file, specifying the renamed jar
ENTRYPOINT ["java", "-jar", "/app/SkillsService.jar"]