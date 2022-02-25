# syntax=docker/dockerfile:1
FROM openjdk:17-oracle

COPY docker-entrypoint.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

ENV APP_NAME code-challenge.jar

ADD target/code-challenge.jar code-challenge.jar

EXPOSE 8080

ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]