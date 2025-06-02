FROM eclipse-temurin:23-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} car-service.jar
ENTRYPOINT ["java", "-jar", "/car-service.jar"]