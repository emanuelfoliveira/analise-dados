FROM adoptopenjdk/openjdk11:alpine
RUN addgroup -S user && adduser -S user -G user
USER user:user
ARG JAR_FILE=target/analise-dados-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
