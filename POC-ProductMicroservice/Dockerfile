#Openjdk is available in dockerhub
FROM openjdk:11
#Port of this microservice
EXPOSE 8888
#Adding the jar of your app
ADD target/product.jar product
#command to run the jar
ENTRYPOINT ["java","-jar","/product"]
