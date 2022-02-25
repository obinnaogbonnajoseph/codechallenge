#!/bin/sh

export JASYPT_ENCRYPTOR_PASSWORD='c@d0ch&l%ng' && ./mvnw clean package

docker-compose down

docker rmi obi-ogbonna-code-challenge:latest

docker-compose up --build