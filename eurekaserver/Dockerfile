FROM openjdk:17
WORKDIR /eurekaserverapp
COPY target/eurekaserver-0.0.1-SNAPSHOT.jar eureka.jar
ENTRYPOINT ["java","-jar","eureka.jar"]