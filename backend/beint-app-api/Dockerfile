# Use the official Gradle base image
FROM gradle:latest AS build
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN gradle build --no-daemon
FROM openjdk:latest
WORKDIR /app
COPY --from=build /app/build/libs/app.jar ./
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]