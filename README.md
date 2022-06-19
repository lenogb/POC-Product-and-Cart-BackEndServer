
# POC- Application
# Docker
Docker is use to containerized all the microservices and run each of it as an image through Dockerfile located on each of their respective folders.
Once Docker is installed, run this command on the command prompt:
1. Go to the directory where Docker-compose file is located. (It should only be located on the root folder of where the microservice's folders are)
2. COMMAND: docker-compose up --build (This will pull up all the necessary images to use (Databases and Kong), and build images out of executable jar files of each microservices (Product and Cart))
3. Jar files are located on the target folder within each microservices folders.

Once services are done building with docker, we may now access the API documentations using Swagger and the Kong manager via "localhost:8000" - I have defined it's port on docker-compose file.

# Kong Gateway
Upon accessing it, please see attached excel file as well of the Kong Gateway documentation that I have made which contains all the lists of configured Services, Routes, Plugins, and Consumers that I used.

To secure all the request prior to proxying, I have considerably used Basic Auth Plugin to identify consumers first, their respective group and control their access.

Along with the enabled Basic-Auth plugin, I used as well Access Control List(ACL) plugin for access control.
Since there is no User Microservice yet to provide user Informations, I created two consumers first using Kong Manager, namely 'admin' and 'customer'.  I have configured two groups of consumers, namely 'ADMIN' group and 'CUSTOMER' group. 
1. Admin group- are consumers who are able to access routes with connection to product management like adding Product, Updating product and getting the lists of the products.
2. Customer group - are consumers who are able to access routes with connection to cart services like creating cart upon consuming request, adding item and removing item from the cart.

# Swagger API Documentation

1. Product Microservice : http://localhost:8887/api/v1/api-docs.html
2. Cart-Gateway Microservice : http://localhost:8889/api/v1/api-docs.html


# EXPOSED ENDPOINTS
# Product Management: (admin)
1. Adding Product (POST) - localhost:8000/admin/manage-product
2. List products (GET) - localhost:8000/admin/manage-product/get-all
3. Get one product (GET) - localhost:8000/admin/manage-product/{Product-ID-to-get}
4. Modify Product (PUT) - localhost:8000/admin/manage-product/{Product-ID-to-modify}

# Get Infomation as a customer
5. List products (GET) - localhost:8000/product/get-all
6. Get one product (GET) - localhost:8000/product/{Product-ID-to-get}
7. Get all processed orders (GET) - localhost:8000/manage-order/get-all

# Cart Service
8. Add to Cart (POST) - localhost:8000/cart/add-to-cart
9. Get all items (GET) - localhost:8000/cart
10. Remove item from cart (DELETE) - localhost:8000/cart
11. Check-out (DELETE) - localhost:8000/cart/check-out