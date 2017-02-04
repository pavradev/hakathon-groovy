FROM java:8

COPY build/libs /libs

CMD ["java", "-jar", "/libs/mydsl-standalone.jar", "/app/pipeline.groovy"]

