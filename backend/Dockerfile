# Use official OpenJDK image as the base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from your target folder to the container
COPY target/backend.jar app.jar

# Expose the port your app is running on
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "app.jar"]
