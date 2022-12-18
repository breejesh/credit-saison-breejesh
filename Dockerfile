# Building locally for now for faster deployments
# FROM maven:3.8.5-openjdk-18 AS build
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:18
COPY CreditSaisonBreejesh-1.0-SNAPSHOT-spring-boot.jar /usr/local/lib/app.jar
# COPY --from=build /home/app/target/CreditSaisonBreejesh-1.0-SNAPSHOT-spring-boot.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]