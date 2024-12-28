FROM openjdk:17
WORKDIR /accountapp
COPY target/accountservice-0.0.1-SNAPSHOT.jar accountservice.jar
ENTRYPOINT ["java","-jar","accountservice.jar"]