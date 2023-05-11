# ---- BUILD ----
# Use the gradle:8.1.1-jdk17-focal image as the base image for the build stage.
FROM gradle:8.1.1-jdk17-focal as BUILD

# Set the working directory to /app.
WORKDIR /app

# Copy the entire project directory to the container's /app directory.
COPY . .

# Set the logging level environment variable to DEBUG.
ENV APP_LOGGING_LEVEL DEBUG

# Build the project using Gradle.
RUN gradle build

# ---- RUN ----
# Use the amazoncorretto:17-alpine image as the base image for the run stage.
FROM amazoncorretto:17-alpine as RUN

# Set the JAR_FILE argument to the location of the built JAR file.
ARG JAR_FILE=build/libs/patbackend-1.0.0.jar

# Set the working directory to /pat-backend.
WORKDIR /pat-backend

# Copy the JAR file from the build stage to the /pat-backend directory.
COPY --from=build /app/${JAR_FILE} pat-backend.jar

# Set the DEBUG_API_OPT environment variable to the DEBUG_OPT argument.
ARG DEBUG_OPT
ENV DEBUG_API_OPT=$DEBUG_OPT

# Start the Java application using the JAR file in the /pat-backend directory and the DEBUG_API_OPT environment variable.
CMD java $DEBUG_API_OPT -jar /pat-backend/pat-backend.jar
