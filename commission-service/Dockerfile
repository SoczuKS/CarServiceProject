FROM eclipse-temurin:23-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} commission-service.jar
ENTRYPOINT ["java", "-jar", "/commission-service.jar"]