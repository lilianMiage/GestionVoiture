Create your spring image first :
You need to package your Spring Boot application into a Docker image before you can run it.
````bash
mvn clean package
````
The Dockerfile provided in this repository is set up to build a Docker image for a Spring Boot application.
You can do this by running the following command in the root directory of your project:
```bash
docker build -t springio/gs-spring-boot-docker .
```
Then run the container, using the docker-compose.yml file provided :
```bash
docker-compose up -d
```
This will start the application and expose it on port 8080. You can access it by navigating to http://localhost:8080 in your web browser.
You can also check the database by navigating to http://localhost:8090 using phpMyAdmin.

To stop the application, you can use the following command:
```bash
docker-compose down -v 
```

