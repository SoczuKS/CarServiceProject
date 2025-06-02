FROM eclipse-temurin:23-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} schedule-service.jar
ENTRYPOINT ["java", "-jar", "/schedule-service.jar"]