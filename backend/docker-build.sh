#!/bin/sh

./mvnw clean package -DskipTests

docker-compose down

docker rmi obi-ogbonna-code-challenge:latest

docker-compose up