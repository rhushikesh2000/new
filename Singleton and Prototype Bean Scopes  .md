## Singleton and Prototype Bean Scopes in Java Spring

Bean Scopes refers to the lifecycle of Bean that means when the object of Bean will be instantiated, how long does that object live, 
and how many objects will be created for that bean throughout. Basically, it controls the instance creation of the bean and it is managed by the spring container.
- Bean Scopes in Spring
  
The spring framework provides five scopes for a bean. We can use three of them only in the context of web-aware Spring ApplicationContext and the rest of the two is available for both IoC container and Spring-MVC container.
The following are the different scopes provided for a bean: 



![Spring-Bean-Scopes ](https://github.com/rhushikesh2000/new/assets/124034778/fc3098f6-8bb4-4d06-a6f6-e298d296910e)



1. Singleton: Only one instance will be created for a single bean definition per Spring IoC container and the same object will be shared for each request made for that bean.
2. Prototype: A new instance will be created for a single bean definition every time a request is made for that bean.
3. Request: A new instance will be created for a single bean definition every time an HTTP request is made for that bean. But Only valid in the context of a web-aware Spring ApplicationContext.
4. Session: Scopes a single bean definition to the lifecycle of an HTTP Session. But Only valid in the context of a web-aware Spring ApplicationContext.
5. Global-Session: Scopes a single bean definition to the lifecycle of a global HTTP Session. It is also only valid in the context of a web-aware Spring ApplicationContext.
   
Let’s us see some of them in detail:

#### Singleton Scope:
---

If the scope is a singleton, then only one instance of that bean will be instantiated per Spring IoC container and the same instance will be shared for each request. That is when the scope of a bean is declared singleton, then whenever a new request is made for that bean, spring IOC container first checks whether an instance of that bean is already created or not. If it is already created, then the IOC container returns the same instance otherwise it creates a new instance of that bean only at the first request. By default, the scope of a bean is a singleton. 
Let’s understand this scope with an example. 

 ![0_RX1BKG2HUxS-JUb3](https://github.com/rhushikesh2000/new/assets/124034778/79c9120f-a6c9-4628-b62e-25aae48f2c92)


- Step1: Lets first create a bean (i.e.), the backbone of the application in the spring framework.

~~~java
  // Java program to illustrate a bean
// created in the spring framework
package bean;
 
public class HelloWorld {
    public String name;
 
    // Create a setter method to
    // set the value passed by user
    public void setName(String name)
    {
        this.name = name;
    }
 
    // Create a getter method so that
    // the user can get the set value
    public String getName()
    {
        return name;
    }
}
~~~

- Step 2: Now, we write a Spring XML configuration file “spring.xml” and configure the bean defined above.
  
~~~java
<!DOCTYPE beans PUBLIC
     "-//SPRING//DTD BEAN 2.0//EN"
     "http://www.springframework.org/dtd/spring-beans-2.0.dtd">
<beans>
     <!--configure the bean HelloWorld.java
         and declare its scope-->
     < bean
         id = "hw"
         class= "bean.HelloWorld"
         scope = "singleton" / >
</beans>
~~~

- Step 3: Finally, write a driver class “Client.java” to request the above bean.

~~~java
// Java program to illustrate
// the client to perform the
// request to the defined bean
 
package driver;
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import bean.HelloWorld;
 
// Client Class to request the
// above defined bean
public class Client {
 
    public static void main(String[] args)
    {
        // Load the Spring XML configuration
        // file into IoC container
        ApplicationContextap= new ClassPathXmlApplicationContext("resources/spring.xml");
 
        // Get the "HelloWorld" bean object
        // and call getName() method
        HelloWorld student1= (HelloWorld)ap.getBean("hw");
 
        // Set the name
        student1.setName("student1");
        System.out.println("Hello object (hello1)"+ " Your name is: "+ student1.getName());
 
        // Get another "HelloWorld" bean object
        // and call getName() method
        HelloWorld student2= (HelloWorld)ap.getBean("hw");
 
        System.out.println("Hello object (hello2)"+ " Your name is: "+ student2.getName());
 
        // Now compare the references to see
        // whether they are pointing to the
        // same object or different object
        System.out.println("'student1' and 'student2'"+ " are referring"+ "to the same object: "+ (student1 == student2));
 
        // Print the address of both
        // object student1 and student2
        System.out.println("Address of object student1: "+ student2);
        System.out.println("Address of object student1: "+ student2);
    }
}
~~~
Output: 
~~~
Hello object (hello1) Your name is: student1
Hello object (hello2) Your name is: student1
'Geeks1' and 'student2' are referring to the same object: true
Address of object student1: bean.HelloWorld@627551fb
Address of object student2: bean.HelloWorld@627551fb
~~~
- Explanation:
  
  When we call the getName() method by using the reference of ‘Geeks1’ and ‘Geeks2’, then we are getting the same outputs.
  This means that both the reference is calling the getName() method of the same object. Furthermore, when we are comparing the reference ‘Geeks1’ and ‘Geeks2’ then output is “true” which means the same object is shared between ‘Geeks1’ and ‘Geeks2’. So it is clear that a new instance of bean (HelloWorld) is created when we made the request the first time and for each new request, the same object is being shared. 
 

#### Prototype Scope:
---
If the scope is declared prototype, then spring IOC container will create a new instance of that bean every time a request is made for that specific bean. 
A request can be made to the bean instance either programmatically using getBean() method or by XML for Dependency Injection of secondary type. Generally, we use the prototype scope for all beans that are stateful, while the singleton scope is used for the stateless beans.

Let’s understand this scope with an example: 

- Step 1: Let us first create a bean (i.e.), the backbone of the application in the spring framework.
  
 ~~~java
// Java program to illustrate a bean
// created in the spring framework
package bean;
 
public class HelloWorld {
    public String name;
 
    // Create a setter method to
    // set the value passed by user
    public void setName(String name)
    {
        this.name = name;
    }
 
    // Create a getter method so that
    // the user can get the set value
    public String getName()
    {
        return name;
    }
}
~~~
- Step 2: Now, we write a Spring XML configuration file “spring.xml” and configure the bean defined above.

 ~~~java
<!DOCTYPE beans PUBLIC
      "-//SPRING//DTD BEAN 2.0//EN"
      "http://www.springframework.org/dtd/spring-beans-2.0.dtd">
 < beans>
      <!--configure the bean HelloWorld.java
          and declare its scope-->
     < bean
         id = "hw"
         class = "bean.HelloWorld"
         scope = "prototype" / >
</ beans>
~~~

- Step 3: Finally, write a driver class “Client.java” to request the above bean.

 ~~~java
// Java program to illustrate
// the client to perform the
// request to the defined bean
 
package driver;
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import bean.HelloWorld;
 
public class Client {
 
    public static void main(String[] args)
    {
        // Load the Spring XML configuration
        // file into IoC container
        ApplicationContext ap= new ClassPathXmlApplicationContext("resources/spring.xml");
 
        // Get the "HelloWorld" bean object
        // and call getName() method
        HelloWorld student1= (HelloWorld)ap.getBean("hw");
 
        // Set the name
        student1.setName("student1");
 
        System.out.println("Hello object (hello1)"+ " Your name is: "+ student1.getName());
 
        // Get another "HelloWorld" bean object
        // and call getName() method
        HelloWorld student2= (HelloWorld)ap.getBean("hw");
 
        System.out.println("Hello object (hello2)"+ "Your name is: "+ student2.getName());
 
        // Now compare the references to see
        // whether they are pointing to the
        // same object or different object
        System.out.println("'student2' and 'student2'"+ "are referring "+ "to the same object: "+ (student1 == student2));
 
        // Print the address of both
        // object student1 and student2
        System.out.println("Address of object student1: "+ student1);
        System.out.println("Address of object student2: "+ student2);
    }
}
~~~
Output: 
~~~
Hello object (hello1) Your name is: student1
Hello object (hello2) Your name is: null
'Geeks1' and 'student2' are referring to the same object: false
Address of object student1: bean.HelloWorld@47ef968d
Address of object student2: bean.HelloWorld@23e028a9
~~~

Explanation: 

When we call getName() method by using the reference ‘Geeks1’ and ‘Geeks2’, then we get different outputs that means both the reference is calling getName() method of a different object. Furthermore, when we are comparing the reference ‘Geeks1’ and ‘Geeks2’ then output is “false” which means both references is referring to a different object. 
So it is clear that a new instance of bean (HelloWorld) is being created at each request made for this bean.

