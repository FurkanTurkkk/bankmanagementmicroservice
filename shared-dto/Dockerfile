FROM openjdk:17
WORKDIR /shareddtoapp
COPY target/shared-dto-1.0.0.jar dtos.jar
ENTRYPOINT ["java","-jar","dtos.jar"]