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
## Implementation
### ERD

<p align="center"><img src="https://github.com/Friend-for-Modern-people/FrimoServer/blob/main/imges/erd.png?raw=true" width="80%" height="80%"></p>

###  Structure

<p align="center"><img src="https://github.com/Friend-for-Modern-people/FrimoServer/blob/main/imges/architecture.png?raw=true" width="50%" height="50%"></p>


