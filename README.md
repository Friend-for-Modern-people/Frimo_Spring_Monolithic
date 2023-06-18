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


### File Tree
```
.
├── Dockerfile
├── Frimo Swagger UI.pdf
├── README.md
├── bin
│   ├── default
│   ├── generated-sources
│   │   └── annotations
│   ├── generated-test-sources
│   │   └── annotations
│   ├── main
│   │   ├── application.properties
│   │   ├── com
│   │   │   └── gachon
│   │   │       └── frimo
│   │   │           ├── FrimoApplication.class
│   │   │           ├── Openfeign.class
│   │   │           ├── config
│   │   │           │   └── SwaggerConfig.class
│   │   │           ├── domain
│   │   │           │   ├── BaseTimeEntity.class
│   │   │           │   ├── diary
│   │   │           │   │   ├── Diary$DiaryBuilder.class
│   │   │           │   │   ├── Diary.class
│   │   │           │   │   └── DiaryRepository.class
│   │   │           │   ├── diaryInterestTag
│   │   │           │   │   ├── DiaryInterestTag$DiaryInterestTagBuilder.class
│   │   │           │   │   ├── DiaryInterestTag.class
│   │   │           │   │   └── DiaryInterestTagRepository.class
│   │   │           │   └── user
│   │   │           │       ├── User$UserBuilder.class
│   │   │           │       ├── User.class
│   │   │           │       └── UserRepository.class
│   │   │           ├── service
│   │   │           │   ├── DiaryInterestTagService.class
│   │   │           │   ├── DiaryService.class
│   │   │           │   └── UserService.class
│   │   │           └── web
│   │   │               ├── DiaryController.class
│   │   │               ├── DiaryInterestTagController.class
│   │   │               ├── UserController.class
│   │   │               └── dto
│   │   │                   ├── DiaryDto$AddDiaryRequestDto$AddDiaryRequestDtoBuilder.class
│   │   │                   ├── DiaryDto$AddDiaryRequestDto.class
│   │   │                   ├── DiaryDto$GetDiaryResponseDto$GetDiaryResponseDtoBuilder.class
│   │   │                   ├── DiaryDto$GetDiaryResponseDto.class
│   │   │                   ├── DiaryDto.class
│   │   │                   ├── DiaryInterestTagDto$AddTagRequestDto$AddTagRequestDtoBuilder.class
│   │   │                   ├── DiaryInterestTagDto$AddTagRequestDto.class
│   │   │                   ├── DiaryInterestTagDto$GetTagResponseDto$GetTagResponseDtoBuilder.class
│   │   │                   ├── DiaryInterestTagDto$GetTagResponseDto.class
│   │   │                   ├── DiaryInterestTagDto.class
│   │   │                   ├── ModelDto$CreateSummaryRequestDto$CreateSummaryRequestDtoBuilder.class
│   │   │                   ├── ModelDto$CreateSummaryRequestDto.class
│   │   │                   ├── ModelDto$GetSummaryResponseDto$GetSummaryResponseDtoBuilder.class
│   │   │                   ├── ModelDto$GetSummaryResponseDto.class
│   │   │                   ├── ModelDto.class
│   │   │                   ├── UserDto$GetUserOnlyInfoResponseDto$GetUserOnlyInfoResponseDtoBuilder.class
│   │   │                   ├── UserDto$GetUserOnlyInfoResponseDto.class
│   │   │                   ├── UserDto$GetUserResponseDto$GetUserResponseDtoBuilder.class
│   │   │                   ├── UserDto$GetUserResponseDto.class
│   │   │                   ├── UserDto$RegistRequestDto$RegistRequestDtoBuilder.class
│   │   │                   ├── UserDto$RegistRequestDto.class
│   │   │                   └── UserDto.class
│   │   └── firebase_frimo_key.json
│   └── test
│       └── com
│           └── gachon
│               └── frimo
│                   └── FrimoApplicationTests.class
├── build
│   ├── bootRunMainClassName
│   ├── classes
│   │   └── java
│   │       └── main
│   │           └── com
│   │               └── gachon
│   │                   └── frimo
│   │                       ├── FrimoApplication.class
│   │                       ├── Openfeign.class
│   │                       ├── config
│   │                       │   └── SwaggerConfig.class
│   │                       ├── domain
│   │                       │   ├── BaseTimeEntity.class
│   │                       │   ├── diary
│   │                       │   │   ├── Diary$DiaryBuilder.class
│   │                       │   │   ├── Diary.class
│   │                       │   │   └── DiaryRepository.class
│   │                       │   ├── diaryInterestTag
│   │                       │   │   ├── DiaryInterestTag$DiaryInterestTagBuilder.class
│   │                       │   │   ├── DiaryInterestTag.class
│   │                       │   │   └── DiaryInterestTagRepository.class
│   │                       │   └── user
│   │                       │       ├── User$UserBuilder.class
│   │                       │       ├── User.class
│   │                       │       └── UserRepository.class
│   │                       ├── service
│   │                       │   ├── DiaryInterestTagService.class
│   │                       │   ├── DiaryService.class
│   │                       │   └── UserService.class
│   │                       └── web
│   │                           ├── DiaryController.class
│   │                           ├── DiaryInterestTagController.class
│   │                           ├── UserController.class
│   │                           └── dto
│   │                               ├── DiaryDto$AddDiaryRequestDto$AddDiaryRequestDtoBuilder.class
│   │                               ├── DiaryDto$AddDiaryRequestDto.class
│   │                               ├── DiaryDto$GetDiaryResponseDto$GetDiaryResponseDtoBuilder.class
│   │                               ├── DiaryDto$GetDiaryResponseDto.class
│   │                               ├── DiaryDto.class
│   │                               ├── DiaryInterestTagDto$AddTagRequestDto$AddTagRequestDtoBuilder.class
│   │                               ├── DiaryInterestTagDto$AddTagRequestDto.class
│   │                               ├── DiaryInterestTagDto$GetTagResponseDto$GetTagResponseDtoBuilder.class
│   │                               ├── DiaryInterestTagDto$GetTagResponseDto.class
│   │                               ├── DiaryInterestTagDto.class
│   │                               ├── ModelDto$CreateSummaryRequestDto$CreateSummaryRequestDtoBuilder.class
│   │                               ├── ModelDto$CreateSummaryRequestDto.class
│   │                               ├── ModelDto$GetSummaryResponseDto$GetSummaryResponseDtoBuilder.class
│   │                               ├── ModelDto$GetSummaryResponseDto.class
│   │                               ├── ModelDto.class
│   │                               ├── UserDto$GetUserOnlyInfoResponseDto$GetUserOnlyInfoResponseDtoBuilder.class
│   │                               ├── UserDto$GetUserOnlyInfoResponseDto.class
│   │                               ├── UserDto$GetUserResponseDto$GetUserResponseDtoBuilder.class
│   │                               ├── UserDto$GetUserResponseDto.class
│   │                               ├── UserDto$RegistRequestDto$RegistRequestDtoBuilder.class
│   │                               ├── UserDto$RegistRequestDto.class
│   │                               └── UserDto.class
│   ├── generated
│   │   └── sources
│   │       ├── annotationProcessor
│   │       │   └── java
│   │       │       └── main
│   │       └── headers
│   │           └── java
│   │               └── main
│   ├── resources
│   │   └── main
│   │       ├── application.properties
│   │       └── firebase_frimo_key.json
│   └── tmp
│       └── compileJava
│           ├── compileTransaction
│           │   ├── backup-dir
│           │   └── stash-dir
│           │       ├── DiaryController.class.uniqueId5
│           │       ├── DiaryService.class.uniqueId7
│           │       ├── ModelDto$CreateSummaryRequestDto$CreateSummaryRequestDtoBuilder.class.uniqueId2
│           │       ├── ModelDto$CreateSummaryRequestDto.class.uniqueId0
│           │       ├── ModelDto$GetSummaryResponseDto$GetSummaryResponseDtoBuilder.class.uniqueId6
│           │       ├── ModelDto$GetSummaryResponseDto.class.uniqueId1
│           │       ├── ModelDto.class.uniqueId3
│           │       └── Openfeign.class.uniqueId4
│           └── previous-compilation-data.bin
├── build.gradle
├── docker-compose copy.yml
├── docker-compose.yml
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── imges
│   ├── architecture.png
│   ├── back_structure.png
│   ├── erd.png
│   ├── spring_archit.png
│   └── test
├── json-simple-1.1.1.jar
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── gachon
    │   │           └── frimo
    │   │               ├── FrimoApplication.java
    │   │               ├── Openfeign.java
    │   │               ├── config
    │   │               │   └── SwaggerConfig.java
    │   │               ├── domain
    │   │               │   ├── BaseTimeEntity.java
    │   │               │   ├── diary
    │   │               │   │   ├── Diary.java
    │   │               │   │   └── DiaryRepository.java
    │   │               │   ├── diaryInterestTag
    │   │               │   │   ├── DiaryInterestTag.java
    │   │               │   │   └── DiaryInterestTagRepository.java
    │   │               │   └── user
    │   │               │       ├── User.java
    │   │               │       └── UserRepository.java
    │   │               ├── service
    │   │               │   ├── DiaryInterestTagService.java
    │   │               │   ├── DiaryService.java
    │   │               │   └── UserService.java
    │   │               └── web
    │   │                   ├── DiaryController.java
    │   │                   ├── DiaryInterestTagController.java
    │   │                   ├── UserController.java
    │   │                   └── dto
    │   │                       ├── DiaryDto.java
    │   │                       ├── DiaryInterestTagDto.java
    │   │                       ├── ModelDto.java
    │   │                       └── UserDto.java
    │   └── resources
    │       ├── application.properties
    │       └── firebase_frimo_key.json
    └── test
        └── java
            └── com
                └── gachon
                    └── frimo
                        └── FrimoApplicationTests.java

75 directories, 137 files

```
* FrimoApplication.java: This file is the main application class of the project. It serves as the entry point for the Spring Boot application and is used to run the application.

* Openfeign.java: OpenFeign is an HTTP client library provided by Spring Cloud. This file is a class that uses OpenFeign to handle communication with remote services.

* config/SwaggerConfig.java: This file is a class that manages Swagger configuration. Swagger is a tool that automatically generates API documentation. In this file, you can configure Swagger UI and API information.

* domain/BaseTimeEntity.java: This file is a common class inherited by JPA entities. It is used to automatically manage the creation and modification timestamps of entities.

* domain/diary/Diary.java: This file defines the Diary entity class. It contains information about diaries and methods for interacting with the database.

* domain/diary/DiaryRepository.java: This file is a repository interface for accessing the Diary entity in the database. It provides methods for CRUD (Create, Read, Update, Delete) operations.

* domain/diaryInterestTag/DiaryInterestTag.java: This file defines the DiaryInterestTag entity class. It contains information about interest tags related to diaries and methods for interacting with the database.

* domain/diaryInterestTag/DiaryInterestTagRepository.java: This file is a repository interface for accessing the DiaryInterestTag entity in the database. It provides methods for CRUD operations.

* domain/user/User.java: This file defines the User entity class. It contains information about users and methods for interacting with the database.

* domain/user/UserRepository.java: This file is a repository interface for accessing the User entity in the database. It provides methods for CRUD operations.

* service/DiaryInterestTagService.java: This file manages the DiaryInterestTag service. It handles the business logic related to DiaryInterestTag.

* service/DiaryService.java: This file manages the Diary service. It handles the business logic related to diaries.

* service/UserService.java: This file manages the User service. It handles the business logic related to users.

* web/DiaryController.java: This file is a controller class that handles API requests related to diaries. It handles requests such as creating, retrieving, updating, and deleting diaries.

* web/DiaryInterestTagController.java: This file is a controller class that handles API requests related to DiaryInterestTag. It handles requests such as creating, retrieving, updating, and deleting interest tags.

* web/UserController.java: This file is a controller class that handles API requests related to users. It handles requests such as creating, retrieving, updating, and deleting users.

* web/dto/DiaryDto.java: This file is a DTO (Data Transfer Object) class for transferring Diary data. It is used for exchanging diary-related information.

* web/dto/DiaryInterestTagDto.java: This file is a DTO class for transferring DiaryInterestTag data. It is used for exchanging information related to interest tags.

* web/dto/ModelDto.java: This file is a DTO class used for common data transfer.

* web/dto/UserDto.java: This file is a DTO class for transferring User data. It is used for exchanging user-related information.

* resources/application.properties: This file is the configuration file for the Spring Boot application. It allows you to specify database connection information and other application settings.

* resources/firebase_frimo_key.json: This file is the key file for Firebase project authentication. It is used for integration with Firebase.

###  System architecture

![image](https://github.com/Friend-for-Modern-people/Frimo_Spring_User/assets/80394866/efd6b739-e7d2-4db4-85af-ee4aa1da69d5)

Two Service Domain
* User Domain
* AI Domain

Each service implements a single business capability within a bounded context and communicates with other services through well-defined APIs
Which means it could be extends as MSA architecture

