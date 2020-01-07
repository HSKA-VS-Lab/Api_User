FROM openjdk:8
ADD target/Api_User-*.jar apiuser.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar", "apiuser.jar"]