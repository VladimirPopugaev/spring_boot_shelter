FROM maven:3.9.1-amazoncorretto-19 as maven-builder

WORKDIR /tmp

COPY . ./

ENV MAVEN_OPTS="-Xmx1024m -XX:MaxMetaspaceSize=128m"

RUN mvn clean package -DskipTests=true

FROM openjdk:19-alpine

ENV JAR_FILE=target/*.jar

COPY --from=maven-builder /tmp/$JAR_FILE /opt/app/
RUN mv /opt/app/*.jar /opt/app/app.jar

WORKDIR /opt/app

RUN chgrp -R 0 /opt/app && \
    chmod -R g=u /opt/app

#ENV PORT 8080

#EXPOSE 8080

ENTRYPOINT ["java","-Dlogstash.host.name=logstash","-Dlogstash.port.number=9999","-jar","app.jar"]