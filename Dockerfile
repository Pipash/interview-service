FROM openjdk:11
VOLUME /tmp
COPY target/interview-service-0.0.1-SNAPSHOT.jar interview-service.jar
ENTRYPOINT ["java","-jar","/interview-service.jar"]