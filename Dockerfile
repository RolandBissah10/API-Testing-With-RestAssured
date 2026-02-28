FROM maven:3.9.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
CMD ["mvn", "clean", "test"]
