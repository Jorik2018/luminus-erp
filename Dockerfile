FROM openjdk:8-alpine

COPY target/uberjar/leiningen-erp.jar /leiningen-erp/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/leiningen-erp/app.jar"]
