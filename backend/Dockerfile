FROM openjdk:17-oracle

ARG JAR_FILE=*.jar
ENV APP_NAME code-challenge.jar

COPY ${JAR_FILE} code-challenge.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=$SPRING_PROFILES_ACTIVE", "-Djasypt.encryptor.password=$JASYPT_ENCRYPTOR_PASSWORD", "-jar", "code-challenge.jar"]