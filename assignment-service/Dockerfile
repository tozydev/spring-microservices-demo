FROM bellsoft/liberica-runtime-container:jre-21-cds-slim-musl
WORKDIR /app
COPY build/libs/assignment-service-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
