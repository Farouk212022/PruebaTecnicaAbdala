FROM openjdk:17-oracle

COPY target/carlosabdala-0.0.1-SNAPSHOT.jar carlosabdala-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/app/carlosabdala.jar"]

EXPOSE 2100/tcp
