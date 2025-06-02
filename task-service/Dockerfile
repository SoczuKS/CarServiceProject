FROM eclipse-temurin:23-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} task-service.jar
ENTRYPOINT ["java", "-jar", "/task-service.jar"]