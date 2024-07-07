FROM openjdk:17-jdk-alpine
WORKDIR /shofydrop
EXPOSE 8080
ADD target/ShofyDrop-0.0.1-SNAPSHOT.jar shofydrop.jar
ENTRYPOINT [ "java","-jar","/shofydrop/shofydrop.jar" ]