FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY build/libs/kmeans-clustering-service-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/kmeans-clustering-service-0.0.1-SNAPSHOT.jar"]