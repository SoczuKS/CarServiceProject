FROM eclipse-temurin:23-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} workshop-service.jar
ENTRYPOINT ["java", "-jar", "/workshop-service.jar"]