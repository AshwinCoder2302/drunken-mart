# Stage 1: Build the application
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the Docker image
FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/drunkenmart-0.0.1-SNAPSHOT.jar
COPY --from=build /app/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
