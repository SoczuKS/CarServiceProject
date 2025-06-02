FROM eclipse-temurin:23-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} service-service.jar
ENTRYPOINT ["java", "-jar", "/service-service.jar"]