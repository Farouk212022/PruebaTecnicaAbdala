FROM openjdk:17-oracle

COPY target/carlosabdala-0.0.1-SNAPSHOT.jar carlosabdala.jar

CMD ["java", "-jar", "carlosabdala.jar"]

EXPOSE 2100/tcp


