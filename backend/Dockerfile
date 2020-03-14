FROM openjdk:8

ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000","-jar","/app.jar"]

RUN set -x && \
    addgroup --gid 1000 appuser && \
    adduser --uid 1000 --disabled-password --ingroup appuser appuser