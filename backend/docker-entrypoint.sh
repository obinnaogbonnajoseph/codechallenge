#!/bin/sh

set -e

exec java \
      -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE \
      -Djasypt.encryptor.password=$JASYPT_ENCRYPTOR_PASSWORD -jar /${APP_NAME}