FROM openjdk:11-jre-buster
COPY target/fuelprices-api-0.0.1-SNAPSHOT.war app.war
EXPOSE 30011
CMD ["java", "-jar", "/app.war"]
