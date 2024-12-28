FROM openjdk:17
WORKDIR /addressapp
COPY target/addressservice-0.0.1-SNAPSHOT.jar addressservice.jar
ENTRYPOINT ["java","-jar","addressservice.jar"]