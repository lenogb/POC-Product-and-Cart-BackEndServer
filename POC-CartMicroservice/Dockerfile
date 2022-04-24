#Openjdk is available in dockerhub
FROM openjdk:11
#Port of this microservice
EXPOSE 8890
#Adding the jar of your app
ADD target/cart.jar cart
COPY target/cart.jar /Docker-Cached_files
#command to run the jar
ENTRYPOINT ["java","-jar","/cart"]
