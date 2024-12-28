FROM openjdk:17
WORKDIR /authserviceapp
COPY target/auth-service-0.0.1-SNAPSHOT.jar authservice.jar
ENTRYPOINT ["java","-jar","authservice.jar"]