## What is JPA, Spring Data and Spring Data JPA

- Spring Data
  ---
  Spring Data is a project driven by Spring that aims at providing a consistent data access layer for various data stores, right from relational to SQL databases.

- Background
  ---
The World of Data has seen enormous growth in the last decade or two. The faster processing mechanisms and specialized storage were invented as the data grew. Then we got graph stores, document stores, key-value stores, and column stores. The specialized stores support specific use cases or a specific type of data. They don’t claim to be a one-stop solution for storing all kinds of data in an application. That gave birth to the concept of Polyglot Persistence.

The downside was those different storage technologies brought in different APIs, configurations, and methods. The JDBC and JPA provide standardization across SQL datastores. But, for the specialized storage technologies, there is no standard specification. Most of the organizations were reluctant to embrace Polyglot Persistence or even the introduction of any single non-RDBMS datastore into the system (many organizations are still). The main reasons behind this are: having no standards across the datastores and the necessary learning curve.


That is where Spring Data comes into the picture. Don’t get this wrong. Spring Data is NOT a Specification or Standard, but it is an Abstraction. It is an umbrella project with many modules (like spring-data-redis, spring-data-mongo, etc.) and a core spring-data-commons module. This abstraction tries to bring consistency between the ways we access data on multiple datastores.

The Spring-Data is an umbrella project having many sub-projects or modules to provide uniform abstractions and uniform utility methods for the Data Access Layer in an application and support a wide range of databases and datastore 


- Java Persistence API
  ---
Java has created a specification called Java Persistent API. It aims towards bringing consistency around accessing different databases. In the last section, we discussed that many types of datastores are available in the market. The same is the case with Relational databases. Before JPA, we used JDBC API to query these relational databases. With JDBC, we have to provide native SQL queries. Writing those String representations of SQL was not only tedious but also error-prone. Moreover, query syntax may change when you change your database.

You may want your production environment based on Oracle, but you need something lightweight in your development or test environment (like PostgreSQL or H2). But, if your application has queries specific to Oracle, your development environment or tests will fail.


- Principle
  ---
The JPA is all about creating Objects which can map to the Database objects. The application will use JPA specification to push or retrieve an object from the database, and underlying JPA implementations will take care of the low-level SQL queries. That is called Object Relational Mapping (ORM). There are many JPA implementations like EclipseLink or Hibernate. With ORM, we use an API to query a particular database entity, and the result is provided in the form of a collection of java objects. Similarly, when we want to push data to the database, we need to populate Java objects and pass them to the API. The underlying ORM implementation is responsible for converting these API calls to the native SQL queries.

- What is Spring Data JPA?
  ---
The Spring Data JPA is one of the many Spring Data projects, and it aims towards bringing consistency in accessing data for relational datastores. 
Many people consider Spring Data JPA is a JPA implementation. In reality, it is false. Spring Data JPA uses a default JPA Implementation called Hibernate. 
The default JPA implementation is configurable, and if we wish, we can use other implementations as well.

- DAO Abstraction (Repositories)
  ---
With Spring Data JPA, we don’t have to write a Data Access Layer or write any SQL statement. Based on JPA specification, the underlying JPA implementation enables the Entity objects and their metadata mapping. It also allows an entity manager responsible for persisting and retrieving entities from the database.

The Spring Data JPA defines Repository interfaces with methods for storing and finding the entity beans from the database. While the interface only defines query methods, Spring at runtime provides proxy implementations for the specified interfaces.


In the background, Spring uses the JPA elements like entities and entity managers but keeps it under the cover and keeps developers free from dealing with them.


- Query Methods
  
The repositories in Spring Data JPA provide a robust and hassle-free way of writing SQL queries Query Methods. The name of the methods declared in the repository interfaces is converted to low-level SQL queries by Spring.

~~~java
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();

    Optional<Employee> findById(Long employeeId);

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findByDepartmentNameAndAgeLessThanOrderByDateOfJoiningDesc(String departmentName, int maxAge);

}
~~~

1. The first method will be select * from employees.
2. The second method puts a where clause on employeeId field.
3. The third one has two WHERE clauses, each for first and last names.
4. The last is a more complex query that finds a list of employees by department name and younger than the given date and sorts the results in descending order of date of joining.
   
We can see the simplicity and the readability of the methods.

- Seamless Integration
  ---
Spring Data JPA also helps your DAO layer integrate and interact with other Spring-based components in your application, like accessing property configurations or auto-wiring the repositories into the application service layer.

It also works perfectly with Spring Boot auto-configuration, where we only need to provide the data source details.

- Summary
  ---
In this tutorial, you understood What JPA, Spring Data, and Spring Data JPA are. Below are the important things you learned today.

- We are in the world of several different database storage technologies, and each one comes with its data access API and drivers.
- Spring Data and its sub-projects provide a uniform way of accessing various data stores.
- Java Persistence API (JPA) is a specification provided by Java for APIs accessing various SQL databases.
- ORM stands for Object Relational Mapping, where a Java Object maps to a database entity, and with APIs, we need to work with objects and not with native queries.
- Spring Data JPA is a sub-project of Spring Data and provides an abstraction over the Data Access Layer using Java Persistence API and ORM implementations like Hibernate.



















