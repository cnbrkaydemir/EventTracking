FROM openjdk:17-jdk

MAINTAINER cnbrkaydemir

COPY target/EventTrackingAPI-0.0.1-SNAPSHOT.jar EventTrackingAPI-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker","EventTrackingAPI-0.0.1-SNAPSHOT.jar"]