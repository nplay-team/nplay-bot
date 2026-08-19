FROM gradle:jdk25-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM eclipse-temurin:25-jre-alpine
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/levelbot.jar /app/levelbot.jar
ENTRYPOINT ["java","-jar","--enable-preview","--sun-misc-unsafe-memory-access=allow","/app/levelbot.jar"]
