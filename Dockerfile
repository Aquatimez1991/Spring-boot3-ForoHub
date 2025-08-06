FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/forohub-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} api_foro_v1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api_foro_v1.jar"]
