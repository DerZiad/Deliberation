FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
EXPOSE 8080
ENTRYPOINT ["mvn","spring-boot:run"]