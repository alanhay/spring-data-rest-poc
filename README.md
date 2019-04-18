## Overview

Sample project to demonstrate a REST API built using the Spring Data Rest module. Exposes a fully featured REST API **without having to write controller end-points for boiler-plate operations (GET/POST etc.)**. It also supports advanced searching, sorting and paging without any code having to be written to support these operations.


```
git clone https://github.com/alanhay/spring-data-rest-poc.git
cd spring-data-rest-poc
mvn spring-boot:run
```

The application will create and populate an HSQL in-memory database. Edit application properties to switch to mysql.

I have been lazy and used Lombok for some stuff so if you want to run via your IDE you'll need the plugin.

Springfox support for Spring Boot 2 looks to be a work in progress so there are no Swagger docs. The application does however bundle the HAL browser which is available at:

```
http://localhost:8080/
```

___
### Basic Functionality
___

So we have a fully-featured REST(in the proper sense) API exposed with the following basic end-points:

```
http://localhost/customers
http://localhost/orders
http://localhost/products
http://localhost/customers/1
http://localhost/customers/1/orders
...etc.

```

Some interesting stuff about it:

##### No Spring MVC controllers required to be written. 

End-points for GET/POST/PUT/PATCH/DELETE created automatically created by the framework. So while the Spring Data project itselves removes much of the boiler plate required at the repository level, the REST extension prevents us having to write boilerplate at the Controller level for basic CRUD.

##### Advanced Querying

Using the QueryDsl extension advanced searching, sorting, paging can be executed against the API, again with no code having to be written: there is no query related code (other than some customisation of the bindings) in any of the repositories exposed as REST resources however we can query be any combination of parameters. Examples:

```
//customers in Edinburgh or Glasgow with surname starting with 'JA'
http://localhost:8080/customers?surname=J&address.town=Edinburgh&address.town=Glasgow&sort=surname,desc&sort=forename

//customers in Glasgow who have placed an order since the specified date.
http://localhost:8080/customers?address.town=Glasgow&lastOrderDate=01/09/2017&sort=lastOrderDate&page=1&size=10 

You can query and sort on any combination of the parameters exposed for a Customer

```

##### No DTO model

We are exposing the model however we can  control what is exposed. So rather than writing a whole DTO layer which 95% mirrors the domain model plus a set of 2 way mapping simple expose the Model and control what is exposed via standard Jackson annotations plus Spring Data projections.

See CustomerMixin.class, OrderMixin.class which allow us to specify serialization/deserialization behaviour for the model (such annotations can also be placed directly on the entity however 'pollutes' the domain model with JSON meta-data).

The concept on Spring Data projections allows us to request different views of the data simply by creating an Interface definition:

```
Projection can be applied to a resource or collection of resources regardless of how it is requested.

e.g.

http://localhost:8080/customers?projection=summary
http://localhost:8080/customers/1?projection=summary
http://localhost:8080/orders/1/customer?projection=summary
http://localhost:8080/customers?address.town&projection=summary

```

##### Posting Data

See the sample JSON files in the folder sample-requests. These can fired to the API using postman.

+ add-customer-json POST to http://localhost:8080/customers
+ add-order-json POST to http://localhost:8080/orders
+ patch-customer-json PATCH to http://localhost:8080/customers


##### Additional Comments:

+ Not an all or nothing approach. Can obviously also use standard Spring MVC controllers for non-resource based operations.
+ The QueryDSL stuff can be used in standard Java code: `Customer c = repo.findOne(q.forename.eq("X").or(q.forename.eq("y")).and(q.address.town="z"));`
+ The QueryDSL HTTP bindings can also be used in standard SPring MVC controllers.
+ I have not yet added a Controller advice to translate JSR-303/330 validation errors into something more client friendly.



