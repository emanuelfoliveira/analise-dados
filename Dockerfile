FROM adoptopenjdk/openjdk11:alpine
MAINTAINER emanuelfoliveira
RUN addgroup -S analisedados && adduser -S analisedados -G analisedados
USER analisedados:analisedados

ARG JAR_FILE=target/analise-dados-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
