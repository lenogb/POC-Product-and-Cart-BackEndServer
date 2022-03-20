#Openjdk is available in dockerhub
FROM openjdk:11
#Port of this microservice
EXPOSE 8889
#Adding the jar of your app
ADD target/logger.jar logger
#command to run the jar
ENTRYPOINT ["java","-jar","/logger"]
