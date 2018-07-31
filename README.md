# Spring RESTful CRUD Service using SpringBoot with H2 InMemory
Comprehensive Spring RESTful Server and Client Application with CRUD Service (H2 inMemory). Contributors are most welcome and you are free to distribute and use. Liked Sources, Just send Thanks to [NEWFOUND SYSTEMS (http://www.newfound-systems.com) support@newfound-systems.com]

## Getting Started

### Prerequisities
* Basic understanding of Spring Framework which includes SpringBoot, Spring Security and Spring Data
* Understanding of JSON or XML.

### Technologies
* JDK 1.8 and above
* Maven 3+
* Jackson Bind
* Apache Tomcat 8+
* Advanced Rest Client or PostMan for REST Client Testing
* Aspect

## Running the server
Spring Boot Main Class, Just run below class like any another Java Code
* rest.sitcom.app.server.SpringBootApplication

### How to access H2 Console
* http://localhost:8080/h2-console

### Configure schema.sql / import.sql for H2
* Application use schema.sql and import.sql to Create / Load data during SpringBoot StartUp

## Running the client
### Run using Sample Java Client
* rest.sitcom.app.client.SitcomRestClient

### Using browser for GET Method
#### 
* http://localhost:8080/sitcom/find/all
* http://localhost:8080/sitcom/find/id/1
* http://localhost:8080/sitcom/find/comprehensive/id/2
* http://localhost:8080/actor/find/multi/fname/George

### Using Advanced REST Client or POSTMAN for POST Method
* http://localhost:8080/sitcom/delete/id/1
* http://localhost:8080/sitcom/add

### Sample Output (JSON Request)
* Output of http://localhost:8080/sitcom/find/all
[
    {
        "id": 1,
        "sitcom_name": "Sienfeld"
    },
    {
        "id": 2,
        "sitcom_name": "Everybody Loves Raymond"
    },
    {
        "id": 3,
        "sitcom_name": "All in the Family"
    }
]

* Outout of http://localhost:8080/sitcom/find/id/1
{
    "id": 1,
    "sitcom_name": "Sienfeld"
}

* Output of http://localhost:8080/sitcom/find/comprehensive/id/2
{
    "sitcoms": [
        {
            "id": 2,
            "sitcom_name": "Everybody Loves Raymond"
        }
    ],
    "actors": [
        {
            "id": 8,
            "sitcom_id": 2,
            "actor_fname": "Ray",
            "actor_lname": "Ray Romano"
        },
        {
            "id": 9,
            "sitcom_id": 2,
            "actor_fname": "Patricia",
            "actor_lname": "Heaton"
        },
        {
            "id": 12,
            "sitcom_id": 2,
            "actor_fname": "George",
            "actor_lname": "Costanza"
        }
    ]
}

* Output of http://localhost:8080/actor/find/multi/fname/George
{
    "sitcoms": [
        {
            "id": 1,
            "sitcom_name": "Sienfeld"
        },
        {
            "id": 2,
            "sitcom_name": "Everybody Loves Raymond"
        }
    ],
    "actors": [
        {
            "id": 2,
            "sitcom_id": 1,
            "actor_fname": "George",
            "actor_lname": "Costanza"
        },
        {
            "id": 12,
            "sitcom_id": 2,
            "actor_fname": "George",
            "actor_lname": "Costanza"
        }
    ]
}

* Output of http://localhost:8080/sitcom/add (POST)
  Request Body
{
        "sitcom_name": "Lucy Show"
}

## Using CURL in LINUX for communications
$ curl --basic --user user:user --request GET http://localhost:8080/sitcom/find/all

$ curl --basic --user user:user --request POST http://localhost:8080/sitcom/add
Result
{
    "result": true,
    "message": "201"
}

## Configure application-dev.properties
* Configure as required

## Versioning
We use [Apache Subversion (https://subversion.apache.org/)] for versioning. 

## Authors
* **Chetan Anand** - *Developer* - [NEWFOUND SYSTEMS http://www.newfound-systems.com]

## License
This project is licensed under the Open Free for all License.

## Acknowledgments
* SO as always
