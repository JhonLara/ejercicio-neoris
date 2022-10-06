FROM adoptopenjdk:16-jre-hotspot
MAINTAINER jhonlara
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} demo-neoris-1.0.jar
ENTRYPOINT ["java","-jar","/demo-neoris-1.0.jar"]