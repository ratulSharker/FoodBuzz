FROM openjdk:11.0.9-jre-slim

EXPOSE 8080

COPY ./target/*.jar food-buzz.jar

ENTRYPOINT ["java", "-jar", "/food-buzz.jar"]