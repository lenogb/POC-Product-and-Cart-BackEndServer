# POC- Application
Docker is use to containerized all the microservices and run each of it as an image through Dockerfile located on each of their respective folders.
Once Docker is installed, run this command on the command prompt:
1. Go to the directory where Docker-compose file is located. (It should only be located on the root folder of where the microservice's folders are)
2. COMMAND: docker-compose up --build (This will pull up all the necessary images to use, and build images out of executable jar files of each microservices)
3. Jar files are located on the target folder within each microservices folders.

The Kong Gateway documentation is in the excel file which contain all the lists of configured Services, Routes, Plugins, and Consumers that I used.

To secure all the request prior to proxying, I have considerably used Basic Auth Plugin to identify consumers first, their respective group and control their access.

Along with the enabled Basic-Auth plugin, I used as well Access Control List(ACL) plugin for access control.
Since there is no User Microservice yet to provide user Informations, I created two consumers first using Kong Manager, namely 'admin' and 'customer'.  I have configured two groups of consumers, namely 'ADMIN' group and 'CUSTOMER' group. 
1. Admin group- are consumers who are able to access routes with connection to product management like adding Product, Updating product and getting the lists of the products.
2. Customer group - are consumers who are able to access routes with connection to cart services like creating cart upon consuming request, adding item and removing item from the cart.




