FROM maven:3.8.3-openjdk-17 AS build  
COPY src /src  
COPY pom.xml .  
RUN mvn clean package


FROM openjdk:17
ARG JAR_FILE=target/projeto-gcm-0.0.1-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} main.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/main.jar"]