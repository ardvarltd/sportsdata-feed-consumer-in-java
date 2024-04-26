FROM maven:3.8.3-openjdk-17 AS build

ADD . /dest

RUN mvn -f /dest/pom.xml clean package

FROM openjdk:17.0.1-jdk

COPY --from=build /dest/target/sportsdata-feed-consumer-example-1.0-SNAPSHOT.jar /app.jar

EXPOSE 8099

CMD ["java", "-jar", "/app.jar"]