FROM openjdk:17-oracle

COPY target/carlosabdala.jar carlosabdala.jar

CMD ["java", "-jar", "/target/carlosabdala.jar"]

EXPOSE 2100/tcp
