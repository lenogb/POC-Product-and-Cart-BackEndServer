#Openjdk is available in dockerhub
FROM openjdk:11
#Port of this microservice
EXPOSE 8888
#Adding the jar of your app
ADD target/cart.jar cart
#command to run the jar
ENTRYPOINT ["java","-jar","/cart"]
