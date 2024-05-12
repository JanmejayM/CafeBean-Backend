FROM openjdk:17
VOLUME /data
WORKDIR /springboot-cafebean/
COPY /target/*.jar /springboot-cafebean/target/
EXPOSE 8080
CMD ["java","-jar","/springboot-cafebean/target/Cafe-Bean-0.0.1-SNAPSHOT.jar"]

