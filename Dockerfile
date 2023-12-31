FROM ubuntu:latest AS build
LABEL authors="lucascavalcante"

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8000

COPY --from=build /target/manager-0.0.1.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]