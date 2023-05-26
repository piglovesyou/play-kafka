
FROM amazoncorretto:17

WORKDIR /app

COPY build/libs/play-kafka-1.0-SNAPSHOT-standalone.jar /app

# Expect subcommand name as the first argument
ENTRYPOINT ["java", "-jar", "play-kafka-1.0-SNAPSHOT-standalone.jar"]