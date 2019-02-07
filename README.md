## Overview

Sample project to demonstrate some Spring Data functionality.


```
git clone git@bitbucket.org:qsengineers/hack-daze-spring-data.git
cd ${clone_directory}
mvn spring-boot:run
```

The application will create and populate an in-memory database. Edit application properties to switch to mysql.

___
### Functionality on data-rest branch:
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
http://localhost:8080/customers?surname=Ja&address.town=Edinburgh&address.town=Glasgow&sort=address.desc&sort=forename

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

##### Additional Comments:

+ Not an all or nothing approach. Can obviously also use standard Spring MVC controllers for non-resource based operations.

