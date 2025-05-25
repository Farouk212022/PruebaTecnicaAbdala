FROM openjdk:17-oracle

COPY target/carlosabdala-0.0.1-SNAPSHOT.jar carlosabdala.jar

COPY wait-for-it.sh /app/wait-for-it.sh

RUN chmod +x /app/wait-for-it.sh

CMD ["java", "-jar", "carlosabdala.jar"]

EXPOSE 2100/tcp


