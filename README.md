# AirFranceTest
Offer technical test

### General Info

A springboot API that exposes two services: 
one that allows to register a user 
one that displays the details of a registered user 


## Installation & Run application in localhost
$ git clone https://github.com/KaramKafri/AirFranceTest.git
$ cd ../path/to/the/file
$ mvn clean install
$ run DemoApplication
Dans la navigateur :
http://localhost:8080/users + objet User
http://localhost:8080/users/{name}

## Comment faire appel aux API REST
avoir un compte postamn 
faire importer les appels REST par ce lien :
https://www.getpostman.com/collections/6a8c95a45018bc9d5eb6

## Comment rajouter des users
des le démarrage de projet , la table person est créé dans une base de donnees H2 avec trois comptes
Dans le fichier src/main/resources/data.sql


## Technologies
Java 11
maven 3.8.1
junit.jupiter
mockito
h2
mapstruct

## project architect
pom.xml
src
├───main
│   ├───java
│   │   └───com
│   │       └───demo
│   │           │   DemoApplication.java
│   │           ├───controller
│   │           │       PersonController.java
│   │           ├───exception
│   │           │       ControllerAdvisor
│   │           │       NameNotFoundException
│   │           │       UserExistException
│   │           ├───model
│   │           │      Person.java
│   │           ├───dao
│   │           │       PersonRepository.java
│   │           ├───dto
│   │           │       PersonDto
│   │           ├───aspect
│   │           │       LoggingAspect
│   │           ├───validator
│   │           │       AdultValidator
│   │           │       ContactNumberValidator
│   │           │       DateOfBirthValidator
│   │           │       FranceValidator
│   │           │
│   │           └───service
│   │                   PersonService.java
│   │                   
│   └───resources
│           application.properties
│           data.sql
└───test
└───java