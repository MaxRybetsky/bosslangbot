FROM openjdk:8-jdk-alpine
COPY target/bosslang-0.0.1-SNAPSHOT.jar application.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "application.jar"]