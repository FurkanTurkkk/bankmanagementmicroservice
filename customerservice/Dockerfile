FROM openjdk:17
WORKDIR /customerapp
COPY target/customerservice-0.0.1-SNAPSHOT.jar customerservice.jar
ENTRYPOINT ["java","-jar","customerservice.jar"]