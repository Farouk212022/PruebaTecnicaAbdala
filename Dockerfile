FROM openjdk:17-oracle

COPY build/libs/carlosabdala-*.jar /app/carlosabdala.jar

CMD ["java", "-jar", "/app/carlosabdala.jar"]

EXPOSE 2100/tcp
