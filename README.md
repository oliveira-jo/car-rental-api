# PROJECT CAR RENTAL

> Status: Developing 

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)


## Objective
The objective of this project is the development of an API to meet the demands of a medium size vehicle rental company. Located in a medium town with just one store.


## Specific Objectives
Facilitate the presentation of the company and it's services to the customer throrgh the platform. CRUD of vehicles and customers


## Project Structure
+ CLIENT / UI (DTO's)
+ CONTROLLERS (DTO's)
+ SERVICES (DTO's)
+ REPOSITORIES (Entities)
+ MODELS (Entities)

## Clone Project and Run Using Docker
Clone the project
```bash
  git clone https://link-para-o-projeto
```

Enter the project diretory
```bash
  docker-compose up --build
```

## Features Developmented
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
+ ✅ [ADMIN] Crud vehicles
+ ✅ [PUBLIC] List all vehicles
+ ✅ Mapping Entities to dto with MapStruct
+ ✅ [PUBLIC] Vehicle search reservation
+ ✅ [PUBLIC] Vehicle select group
+ ✅ [PRIVATE] Vehicle confirm reservation

## Entities
<table>
  <tr>
    <th>User</th>
  </tr>
  <tr>
    <td>
      id : UUID <br>
      login : String <br>
      password : String <br>
      role : UserRole <br>
    </td>
  </tr>
</table>    

<table>
  <tr>
    <th>Category</th>
  </tr>
  <tr>
    <td>
      id : UUID <br>
      categoryName : String <br>
      datails : String <br>
      numBigSuitCases : Integer <br>
      numSmallSuitCases : Integer <br>
      numOfPeople: Boolean <br>
      complete: Boolean <br>
      value: Float
    </td>
  </tr>
</table>    

<table>
  <tr>
    <th>Vehicle</th>
  </tr>
  <tr>
    <td>
      id : UUID <br>
      model : String <br>
      plate : String <br>
      color : String <br>
      complete : Boolean <br>
      mileage : Integer <br>
      ative : Boolean <br>
      Category : Category <br>
    </td>
  </tr>
</table>    

<table>
  <tr>
    <th>Reservation</th>
  </tr>
  <tr>
    <td>
      id : UUID <br>
      pichUpData : LocalDateTime <br>
      returnDate : LocalDateTime <br>
      dailyRentalValue : Float <br>
      qtdDays : Long <br>
      totalValue : BigDecimal <br>
      reservationStatus : ReservationStatus <br>
      user : User <br>
      category : Category <br>
    </td>
  </tr>
</table>    

## Dadabase Association
```
( Category )  1 <-> N  ( Vehicle )
( User )  1 <-> N  ( Reservation )
( Category )  1 <-> N  ( resercation )
```

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

* Register a Reservation
* Cancel a Reservation

## Technologies Used
* Java
* Spring Boot Starter Web
* Spring Boot Devtools
* Spring Boot Starter Data JPA
* MySQL Data Base
* MySQL Driver
* Spring Boot Starter Security
* Java Docks
* MapStruct

## API Documentations

### Endpoints Users:

```http
  POST /auth/login
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `login` | `string` | **Required**. User login |
| `password` | `string` | **Required**. User password |


```http
  POST /auth/register
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `login` | `string` | **Required**. User login |
| `password` | `string` | **Required**. User password |


```http
  GET /user/all
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |


```http
  GET /user/me
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |


```http
  PUT /user
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |


### Endpoints Vehicle:
```http
  GET /vehicle
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |


```http
  POST /vehicle
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |
| `model` | `string` | **Required**. Vehicle model |
| `plate` | `string` | **Required**. Vehicle plate |
| `color` | `string` | **Required**. Vehicle color |
| `complete` | `boolean` | **Required**. Vehicle is complete? |
| `mileage` | `integer` | **Required**. Vehicle mileage |
| `ative` | `boolean` | **Required**. Vehicle is active? |
| `categoryId` | `string` | **Required**. Category UUID |


```http
  PUT /vehicle/{id}
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |
| `id` | `string` | **Required**. Vehicle UUID |
| `model` | `string` | **Required**. Vehicle model |
| `plate` | `string` | **Required**. Vehicle plate |
| `color` | `string` | **Required**. Vehicle color |
| `complete` | `boolean` | **Required**. Vehicle is complete? |
| `mileage` | `integer` | **Required**. Vehicle mileage |
| `ative` | `boolean` | **Required**. Vehicle is active? |
| `categoryId` | `string` | **Required**. Category UUID |


```http
  DELETE /vehicle/{id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token` | `string` | **Required**. Token retornado pelo login |
| `id`      | `string` | **Required**. Vehicle UUID |


### Endpoints Category:
```http
  GET /category
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |


```http
  POST /category
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |
| `categoryName` | `string` | **Obrigatório**. Token retornado pelo login |
| `datails` | `string` | **Required**. Vehicle model |
| `numBigSuitCases` | `integer` | **Required**. Vehicle plate |
| `numSmallSuitCases` | `integer` | **Required**. Vehicle color |
| `numOfPeople` | `integer` | **Required**. Vehicle is complete? |
| `complete` | `boolean` | **Required**. Vehicle mileage |
| `value` | `float` | **Required**. Vehicle is active? |


```http
  PUT /category/{id}
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |
| `id`      | `string` | **Required**. Category UUID  |
| `categoryName` | `string` | **Obrigatório**. Token retornado pelo login |
| `datails` | `string` | **Required**. Vehicle model |
| `numBigSuitCases` | `integer` | **Required**. Vehicle plate |
| `numSmallSuitCases` | `integer` | **Required**. Vehicle color |
| `numOfPeople` | `integer` | **Required**. Vehicle is complete? |
| `complete` | `boolean` | **Required**. Vehicle mileage |
| `value` | `float` | **Required**. Vehicle is active? |


```http
  DELETE /category/{id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token` | `string` | **Required**. Token retornado pelo login |
| `id`      | `string` | **Required**. Category UUID  |


### Endpoints Reservation:

```http
  POST /reservation
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token` | `string` | **Required**. Token retornado pelo login |


```http
  GET /reservation/all
```
| Parâmetro   | Tipo       | Descrição                                |
| :---------- | :--------- | :--------------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login       |


```http
  GET /reservation/{id}
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |
| `id`      | `string` | **Required**. Reservation UUID  |


```http
  DELETE /reservation/{id}
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Required**. Token retornado pelo login |
| `id` | `string` | **Required**. Reservation UUID |





