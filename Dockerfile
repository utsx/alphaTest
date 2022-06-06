FROM openjdk:17-jdk-slim

COPY build/libs/utsx-0.0.1-SNAPSHOT.jar /alpha.jar

CMD ["java", "-jar", "/alpha.jar"]
