#Måste uppdatera service-namnen!!

FROM openjdk:15-alpine
COPY target/artistsservice-0.0.1-SNAPSHOT.jar  artistsservice.jar
#COPY /target/classes /app/
#ENTRYPOINT [ "java", "." , /app/core:/app/modules", "-m", "se.andreasson.core/se.andreasson.core.SimpleServer"]
ENTRYPOINT [ "java", "-jar", "/artistsservice.jar"]

FROM openjdk:15-jdk-alpine
EXPOSE 5050
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]