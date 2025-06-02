FROM eclipse-temurin:23-jre
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} server.jar
ENTRYPOINT ["java", "-jar", "/server.jar"]