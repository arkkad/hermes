FROM maven:3.6.1-jdk-8
ADD . /tmp
WORKDIR /tmp
RUN mvn clean package

FROM openjdk:8-jdk-alpine
COPY --from=0 /tmp/target/hermes-1.0-SNAPSHOT.jar /opt/
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/hermes-1.0-SNAPSHOT.jar"]

