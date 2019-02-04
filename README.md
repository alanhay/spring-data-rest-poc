## Overview

Sample project to demonstrate some Spring Data functionality.


```
git clone git@bitbucket.org:qsengineers/hack-daze-spring-data.git
cd ${clone_directory}
mvn spring-boot:run
```

___
### Functionality on master Branch:
___

##### Spring Data and Query DSL

While it easy to create queries in Spring Data via either derivation from the method name  
or by explicitly specifying using the `@Query` annotation, Spring Data also supports integration with the
QueryDSL library. With Query DSL in support in place we can query by any combination of properties without 
having to write any query methods. 

`Example`

##### Spring Data Web Integration and QueryDSL

The Spring data web integration allows us to use this QueryDSL functionality in Controllers
without us having to write any code. We can call the `/search` endpoint on our Customers Controller with any combination of parameters 
(note on the following we do a 'like' look up on Surname via the customization in the `CustomerRepository` class).

```
http://localhost:8080/customers?surname=Ja&address.town=Edinburgh&address.town=Glasgow&sort=address.desc&sort=forename&page=1&size=10
```

##### Exposing Model directly i.e. without DTO and associated mappings

The sample controllers are returning Entity classes directly rather than DTO classes. There is
nothing in this sample project that would prevent DTOs being returned however that obviously requires the creation
of a bunch of DTO classes (which typically largely duplicate the domain model) and associated 2-way mappers. 

As alternative I have used the concept of Jackson's JsonView together with some Jackson mix-in classes that allow us 
to control  the JSON representation of the Entity. With such an approach then we can separate the model from it's JSON 
representation without the duplicate DTO classes and associated data mappers.  

___
### Functionality on data-rest branch:
___

While the Spring Data project removes much of the boiler plate required at the repository level, we are still
required to write a lot of pretty similar code at the Controller level. In this sample project for
example, the Customers contoller needs to be extended to handle PUT/PATCH/DELETE operations and we would
have to write similar Controller logic for Orders and Products etc. which is all a bit tedious.

The Spring Data Rest extension extends the repository abstraction to the API level by exposing REST endpoints
for any repositories we wish to expose as REST repositories.

If you switch to this branch and run the application it can be  seen that all of the functionality detailed above is 
still available however there are no controller classes.

```
http://localhost:8080/customers?surname=Ja&address.town=Edinburgh&address.town=Glasgow&sort=address.desc&sort=forename
http://localhost:8080/customers
http://localhost:8080/customers/1/orders
http://localhost:8080/orders/
http://localhost:8080/products
```

In addition to searching we also have automatically generated end-points for POST/PUT/PATCH/DELETE for each of our entities.

`attach postman collection`

Some Notes on Spring Data Rest

- HATEOS
- Event Listeners
etc.
