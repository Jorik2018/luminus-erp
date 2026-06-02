FROM eclipse-temurin:11-jre

WORKDIR /luminus-erp

COPY target/uberjar/luminus-erp.jar app.jar

EXPOSE 3000

CMD ["java", "-jar", "app.jar"]
