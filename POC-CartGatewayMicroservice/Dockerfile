#Openjdk is available in dockerhub
FROM openjdk:11
#Port of this microservice
EXPOSE 8889
#Adding the jar of your app
ADD target/cartgateway.jar cartgateway
COPY target/cartgateway.jar /Docker-Cached_files
#command to run the jar
ENTRYPOINT ["java","-jar","/cartgateway"]
