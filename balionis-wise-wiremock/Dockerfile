FROM openjdk:17-jdk-alpine@sha256:57c2bd64588e39a7d00581b094136e4b13d2056b5a4f5a0fc39f23f17f1b6965

WORKDIR /app

COPY ./build/libs/balionis-wise-wiremock-all.jar ./app.jar

RUN apk --no-cache add curl

EXPOSE 9090
EXPOSE 8080
EXPOSE 9000

ENTRYPOINT java $JAVA_OPTS -jar app.jar
