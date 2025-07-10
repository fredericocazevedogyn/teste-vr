FROM openjdk:21-jdk-slim
COPY target/*.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]
