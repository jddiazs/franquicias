FROM openjdk:11
COPY build/libs/franquicias-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]