FROM openjdk:21

WORKDIR /app

COPY ./auth/target/auth-1.0-SNAPSHOT.jar /app/auth-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "auth-1.0-SNAPSHOT.jar"]


