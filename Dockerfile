FROM openjdk:11-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/spring-jpa-rest-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} spring-jpa-rest-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/spring-jpa-rest-0.0.1-SNAPSHOT.jar"]