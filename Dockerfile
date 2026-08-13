# ==============================
# Build Stage
# ==============================
#FROM gradle:8.10-jdk21 AS build

#WORKDIR /app

#COPY build.gradle settings.gradle ./
#COPY gradle ./gradle

#RUN gradle dependencies --no-daemon

#COPY src ./src

#RUN gradle clean bootJar --no-daemon


# ==============================
# Runtime Stage
# ==============================
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
