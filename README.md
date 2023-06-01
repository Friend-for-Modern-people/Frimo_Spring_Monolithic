# FrimoServer

This repository implemented a backstage to communicate with Frimo. It uses HTTP and Spring boot For the structure of the directory, refer to spring-boot architecture.

<p align="center"><img src="https://github.com/Friend-for-Modern-people/FrimoServer/blob/main/imges/spring_archit.png?raw=true" width="30%" height="30%"></p>


## Run on Localhost

FrimorServer only supports HTTP environment and mySQL, so for testing, The db setting should be prioritized. Put the details of the db into **properties**.

#### Quick Start with Docker compose
1. Change the file name 'docker-compose copy.yml' to 'docker-compose.yml'
2. Type the properties that needed (!!DO NOT MAKE SPACE BETWEEN )
```yml
version: '3'
services:
  web:
    build: ./
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:{PORT(ex, 3306)}?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
      - SPRING_DATASOURCE_USERNAME=exampleuser
      - SPRING_DATASOURCE_PASSWORD=exmamplePW
      - MODEL_SERVER=example.model.com
    ports:
      - "8080:8080"
    depends_on:
      - db
  db:
    image: mysql:latest
    environment:
      - MYSQL_DATABASE=exampleDB
      - MYSQL_ROOT_PASSWORD=examplePW
```
3. change the file ***application.properties*** just like below
```yml
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update

openfeign.url=${MODEL_SERVER}

```
4. Then type this command on terminal``` docker compose -f docker-compose.yml up -d ``` to 



    If you want to close with it then type this command ``` docker compose down -v --rmi=local ```

***!! And you can also use with .env file if you have own Databases***

## API Documentation
This Repository already have swagger libs, so that you can use this url 

```
https://{HOST DOMAIN}/swagger-ui/index.html#/
``` 
Then you can see this page for start.

![image](https://github.com/Friend-for-Modern-people/Frimo_Spring_Monolithic/assets/80394866/638aeb2a-71c6-4c65-9949-2b5dc0efb9ad)

Just click and test below APIs.

![image](https://github.com/Friend-for-Modern-people/Frimo_Spring_Monolithic/assets/80394866/194f0867-4c20-4ffd-9a75-8056fbe7d75a)

If you want check some more details then, pdf is ready for you.

## Implementation
### ERD

<p align="center"><img src="https://github.com/Friend-for-Modern-people/FrimoServer/blob/main/imges/erd.png?raw=true" width="80%" height="80%"></p>

###  Structure

<p align="center"><img src="https://github.com/Friend-for-Modern-people/FrimoServer/blob/main/imges/architecture.png?raw=true" width="50%" height="50%"></p>


