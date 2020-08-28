FROM maven:3.6.1-jdk-8
ADD . /tmp
WORKDIR /tmp
RUN mvn clean package

FROM java:8

COPY --from=0 /tmp/target/hermes-mail-sender-1.0-SNAPSHOT.jar /opt/
EXPOSE 8081

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/hermes-mail-sender-1.0-SNAPSHOT.jar"]

