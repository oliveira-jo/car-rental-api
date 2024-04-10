# CAR RENTAL PROJECT


> Status: Developing ⚠️


## Objective
The objective of this project is the development of an API to meet the demands of a medium size vehicle rental company. Located in a medium town with just one store.


## Specific Objectives
Facilitate the presentation of the company and it's services to the customer throrgh the platform. CRUD of vehicles and customers


## Project Tamplate MVC
+ CLIENT / UI (DTO's)
+ CONTROLLERS (DTO's)
+ SERVICES (DTO's)
+ REPOSITORIES (Entities)
+ MODELS (Entities)


## Features Under Development
+ Project Struture
+ Spring Security 
+ 


## Entities

Vehicle |
---------------------
Model : String 
Vehicle : Vehicle 
Value : Float 
Datails : String 

User |
---------------------
Name : String 
License : String 
Email : String 
Password: String 


## Desired Operations
* Register User
Register a user in the api passing datas through JSON 
* Read User
Read a user in the api, for name or list of users
* Update User
Update a user in the api passing the identification
* Delete User
Delete a user in the api passing the identification

* Register Vehicle
Register a vehicle in the api passing datas through JSON
* Read Vehicle
Read a vehicle in the api, for name or list of users
* Update Vehicle
Update a vehicle in the api passang the identification
* Delete Vehicle
Delete a vehicle in the api passing the identification


## Technologies Used
* Java
* Spring Boot Starter Web
* Spring Boot Devtools
* Spring Boot Starter Data JPA
* MySQL Data Base
* MySQL Driver
* Spring Boot Starter Security
* Java Docks


## Configuring the Database
### In the file Application.properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/car-rental 
spring.datasource.username=YOUR-USER
spring.datasource.password=YOUR-PASSWORD
spring.jpa.hibernate.ddl-auto=update 
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### SQP Roles for the users
```
INSERT INTO ROLE (rolename) VALUES("SELECT");
INSERT INTO ROLE (rolename) VALUES("INSERT");
INSERT INTO ROLE (rolename) VALUES("UPDATE");
INSERT INTO ROLE (rolename) VALUES("DELETE");
```








