# CAR RENTAL PROJECT

> Status: Developing 

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)


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
+ ✅ Initialize the project (Web + security + data + mysql)
+ ✅ Create entities (User with Role)
+ ✅ Create repositories
+ ✅ Configure the spring security for token JWT
+ ✅ Generate key token
+ ✅ [PUBLIC] Create login
+ ✅ Admin user automation
+ ✅ [PUBLIC] Register a new user
+ ✅ [ADMIN] List all user
+ ✅ [PRIVATE] Update user 
+ ✅ [PRIVATE] Delete user 
+ ✅ [ADMIN] Crud categories
+ ✅ [PUBLIC] List all categories
+    [ADMIN] Crud vehicles
+    [PUBLIC] List all vehicles

## Entities

<table>
  <tr>
    <th>User</th>
  </tr>
  <tr>
    <td>
      Name : String <br>
      License : String <br>
      Email : String <br>
      Password: String <br>
    </td>
  </tr>
</table>    

<table>
  <tr>
    <th>Category</th>
  </tr>
  <tr>
    <td>
      datails : String <br>
      numBigSuitCases : int <br>
      numSmallSuitCases : int <br>
      numOfPeople : int <br>
      complete: boolean <br>
      value: float
    </td>
  </tr>
</table>    

<table>
  <tr>
    <th>Vehicle</th>
  </tr>
  <tr>
    <td>
      model : String <br>
      plate : String <br>
      color : String <br>
      complete : boolean <br>
      mileage : int <br>
      ative : boolean <br>
      Category : category <br>
    </td>
  </tr>
</table>    

## Dadabase Association
CATEGORYES  1 <-> N  VEHICLE

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

## API Endpoints
The API provides the following endpoints:

```markdown
POST /auth/login - Login into the App

POST /auth/register - Register a new user into the App
```







