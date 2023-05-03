FROM openjdk:18
COPY target/FoodMenuService-0.0.1-SNAPSHOT.jar /usr/local/app/
WORKDIR /usr/local/app
EXPOSE 8081
CMD ["java", "-jar", "FoodMenuService-0.0.1-SNAPSHOT.jar"]
