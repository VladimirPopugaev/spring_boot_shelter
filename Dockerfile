# AS <NAME> to name this stage as maven
FROM maven:3.9.1-amazoncorretto-19 AS maven
LABEL MAINTAINER="popugaevvn1@gmail.com"

WORKDIR /
COPY . /
# Compile and package the application to an executable JAR
RUN mvn package

# For Java 19,
FROM amazoncorretto:19.0.2-alpine

ARG JAR_FILE=spring_boot_shelter-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","spring_boot_shelter-0.0.1-SNAPSHOT.jar"]