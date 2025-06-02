FROM eclipse-temurin:23-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} database-service.jar
ENTRYPOINT ["java", "-jar", "/database-service.jar"]