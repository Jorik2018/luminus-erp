FROM eclipse-temurin:8-jre

WORKDIR /leiningen-erp

COPY target/uberjar/leiningen-erp.jar app.jar

EXPOSE 3000

CMD ["java", "-jar", "app.jar"]
