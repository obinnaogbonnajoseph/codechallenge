# CodeChallenge API

This is a rest API built with Spring Boot (Java) framework.

You'll need an environment variable, JASYPT_ENCRYPTOR_PASSWORD or command line variable, jasypt.encryptor.password, to successfully run the project.

## Run the Project:
```bash
    ./mvnw -Djasypt.encryptor.password=password_value spring-boot:run
```
For test, run the command:
```bash
    ./mvnw -Djasypt.encryptor.password=password_value spring-boot:test
```