FROM openjdk:17
WORKDIR /transactionserviceapp
COPY target/transactionservice-0.0.1-SNAPSHOT.jar transactionservice.jar
ENTRYPOINT ["java","-jar","transactionservice.jar"]