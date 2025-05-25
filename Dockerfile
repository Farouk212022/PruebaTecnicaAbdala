FROM openjdk:17-oracle

COPY build/libs/carlosabdala-*.jar /app/carlosabdala.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 2100