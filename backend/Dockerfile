FROM eclipse-temurin:17.0.5_8-jdk AS build

COPY build/libs/backend-0.0.0.jar app.jar

ENV JVM_XMX=1G

EXPOSE 8080

ENTRYPOINT exec java \
        -Xmx${JVM_XMX} \
        -Xms${JVM_XMX} \
        -jar app.jar