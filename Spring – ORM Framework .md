Spring-ORM is a technique or a Design Pattern used to access a relational database from an object-oriented language. ORM (Object Relation Mapping) covers many persistence technologies. They are as follows:


![Screen-Shot-2019-02-27-at-14 09 03-e1599722589875](https://github.com/rhushikesh2000/new/assets/124034778/fd0f2014-99ac-4555-ac2a-08b85956c7db)



- JPA(Java Persistence API):
 It is mainly used to persist data between Java objects and relational databases. It acts as a bridge between object-oriented domain models and relational database systems.
- JDO(Java Data Objects):
It is one of the standard ways to access persistent data in databases, by using plain old Java objects (POJO) to represent persistent data.
- Hibernate :
It is a Java framework that simplifies the development of Java applications to interact with the database.
- Oracle Toplink, and iBATIS:
Oracle TopLink is a mapping and persistence framework for Java development.
For the above technologies, Spring provides integration classes so that each of these techniques can be used following Spring principles of configuration, and easily integrates with Spring transaction management.

For each of the above technologies, the configuration consists of injecting the DataSource bean into some kind of SessionFactory or EntityManagerFactory, etc. For pure JDBC(Java Database Connectivity), integration classes are not required apart from JdbcTemplate because JDBC only depends on a DataSource.

If someone wants to use an ORM like JPA(Java Persistence API) or Hibernate then you do not need spring-JDBC, but only this module.

Note: The Spring Framework is an application framework and also an inversion of the control container for the Java platform. The framework’s core features can be used by any of the Java applications, but there are some extensions for building web applications on top of the Java EE (Enterprise Edition) platform.

- Advantages of the Spring Framework About ORM Frameworks
  ---
1. Due to the Spring framework, you do not need to write extra codes before and after the actual database logic such as getting the connection, starting the transaction, committing the transaction, closing the connection, etc.
2. Spring has an IoC(Inversion of control) approach which makes it easy to test the application.
3. Spring framework provides its API for exception handling along with the ORM framework.
4. By using the Spring framework, we can wrap our mapping code with an explicit template wrapper class or AOP(Aspect-oriented programming) style method interceptor.
