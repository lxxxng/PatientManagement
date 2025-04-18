FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

# make the downloaded dependencies available online so future build not need download again
RUN mvn dependency:go-offline -B       

COPY src ./src

# create a package with the application and all dependencies
# this will create a single jar file
RUN mvn clean package 

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/analytics-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4002

ENTRYPOINT ["java", "-jar", "app.jar"]



