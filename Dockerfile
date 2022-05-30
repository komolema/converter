FROM openjdk:18-jdk-alpine
MAINTAINER karabo
COPY target/converter-0.0.1-SNAPSHOT.jar converter-server.jar
ENTRYPOINT ["java", "-jar", "/converter-server.jar"]