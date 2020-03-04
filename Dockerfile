FROM openjdk:8-jre-alpine
EXPOSE 8083
COPY /target/mailer-0.0.1-SNAPSHOT.jar mailer.jar
ENTRYPOINT ["java", "-jar", "mailer.jar"]