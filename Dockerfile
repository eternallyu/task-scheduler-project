########################
# 1) Gradle
########################
FROM amazoncorretto:21 AS builder
WORKDIR /home/gradle/project

COPY gradlew gradlew
COPY gradle gradle
COPY . .
RUN chmod +x gradlew
RUN ./gradlew bootJar -x test --no-daemon

########################
# 2) Backend-service
########################
FROM amazoncorretto:21 AS backend
WORKDIR /app
COPY --from=builder /home/gradle/project/task-scheduler-backend/build/libs/*.jar app.jar
EXPOSE 8001
ENTRYPOINT ["java","-jar","app.jar"]

########################
# 3) Scheduler-service
########################
FROM amazoncorretto:21 AS scheduler
WORKDIR /app
COPY --from=builder /home/gradle/project/task-scheduler-service/build/libs/*.jar app.jar
EXPOSE 8002
ENTRYPOINT ["java","-jar","app.jar"]

########################
# 4) Email-sender
########################
FROM amazoncorretto:21 AS email-sender
WORKDIR /app
COPY --from=builder /home/gradle/project/task-scheduler-email-sender/build/libs/*.jar app.jar
EXPOSE 8003
ENTRYPOINT ["java","-jar","app.jar"]
