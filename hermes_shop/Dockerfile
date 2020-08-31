FROM openjdk:8-jdk-alpine
COPY target/hermes-1.0-SNAPSHOT.jar /app/app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]