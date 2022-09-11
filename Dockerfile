FROM maven:3.8.4-jdk-8-slim AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package -Dmaven.test.skip=true

FROM adoptopenjdk/openjdk8:alpine-slim
RUN mkdir /opt/app
COPY --from=MAVEN_BUILD target/information_tape-0.0.1-SNAPSHOT.jar /opt/app

ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","/opt/app/information_tape-0.0.1-SNAPSHOT.jar"]