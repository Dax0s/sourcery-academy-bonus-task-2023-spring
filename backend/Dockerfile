FROM amazoncorretto:17

ENV JAVA_HOME=/usr/lib/jvm/java-17-amazon-corretto

WORKDIR /app

COPY . .

RUN ./gradlew build

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./build/libs/backend-0.0.1-SNAPSHOT.jar"]