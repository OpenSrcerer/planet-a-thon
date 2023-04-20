# ---- BUILD ----
FROM amazoncorretto:17-alpine as BUILD

WORKDIR /app

COPY . .

ENV APP_LOGGING_LEVEL DEBUG

RUN ./gradlew build

# ---- RUN ----
FROM amazoncorretto:17-alpine as RUN

ARG JAR_FILE=build/libs/patbackend-1.0.0.jar

WORKDIR /pat-backend

COPY --from=build /app/${JAR_FILE} pat-backend.jar

ARG DEBUG_OPT
ENV DEBUG_API_OPT=$DEBUG_OPT

CMD java $DEBUG_API_OPT -jar /pat-backend/pat-backend.jar