FROM openjdk:17-jdk-slim  as base
FROM base as development
COPY . /archive
ENTRYPOINT ["java","-jar","/archive/target/ArchiveSystem-0.0.1-SNAPSHOT.jar"]
FROM base as production
COPY ./target/ArchiveSystem-0.0.1-SNAPSHOT.jar /archive/Archive.jar
ENTRYPOINT ["java","-jar","/archive/Archive.jar"]

