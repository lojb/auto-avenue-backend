FROM maven:3.9.1-eclipse-temurin-17 AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean install

FROM maven:3.9.1-eclipse-temurin-17
RUN mkdir /app
COPY --from=build /project/target/autoavenue-0.0.1-SNAPSHOT.jar /app/autoavenue-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "/app/autoavenue-0.0.1-SNAPSHOT.jar"]
