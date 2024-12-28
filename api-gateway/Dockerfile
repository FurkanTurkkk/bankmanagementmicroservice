FROM openjdk:17
WORKDIR /apigatewayapp
COPY target/api-gateway-0.0.1-SNAPSHOT.jar apigateway.jar
ENTRYPOINT ["java","-jar","apigateway.jar"]