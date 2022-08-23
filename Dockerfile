FROM openjdk:17
WORKDIR ./app
EXPOSE 8081
ADD ./target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]