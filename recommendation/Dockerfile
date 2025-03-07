FROM openjdk:21

WORKDIR /app

COPY ./recommendation/target/recommendation-service-1.0-SNAPSHOT.jar /app/recommendation-service-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "recommendation-service-1.0-SNAPSHOT.jar"]


