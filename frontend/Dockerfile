FROM adoptopenjdk/openjdk11
VOLUME /tmp
ADD target/frontend-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom", "-Deureka.client.serviceUrl.defaultZone=http://registry:1111/eureka/", "-jar", "/app.jar"]