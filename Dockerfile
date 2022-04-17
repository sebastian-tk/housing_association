FROM openjdk:16-alpine
WORKDIR ./app
EXPOSE 8080
ADD ./target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]