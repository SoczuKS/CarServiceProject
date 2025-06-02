FROM eclipse-temurin:23-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} frontend-service.jar
ENTRYPOINT ["java", "-jar", "/frontend-service.jar"]