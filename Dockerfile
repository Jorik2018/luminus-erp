FROM eclipse-temurin:11-jre

WORKDIR /leiningen-erp

COPY target/uberjar/leiningen-erp.jar app.jar

EXPOSE 3000

CMD ["java", "-jar", "app.jar"]
