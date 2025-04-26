# a lightweight JDK base image
FROM openjdk:17-jdk-slim

# environment variables (optional)
ENV SERVER_PORT=8080

# thats the working directory created inside the container
WORKDIR /app

# copy your Spring Boot jar file into the container
COPY target/*.jar app.jar

# 5. Expose the port your app runs on
EXPOSE 8080

# 6. Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]