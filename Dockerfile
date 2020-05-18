FROM maslick/minimalka:jdk11
COPY /target/library-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]