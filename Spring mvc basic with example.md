## Spring – MVC Framework

Spring MVC Framework follows the Model-View-Controller architectural design pattern which works around the Front Controller i.e. the Dispatcher Servlet. 
The Dispatcher Servlet handles and dispatches all the incoming HTTP requests to the appropriate controller. 
It uses @Controller and @RequestMapping as default request handlers. The @Controller annotation defines that a particular class is a controller.
@RequestMapping annotation maps web requests to Spring Controller methods. The terms model, view, and controller are as follows:

- Model: The Model encapsulates the application data.
- View: View renders the model data and generates HTML output that the client’s browser can interpret.
- Controller: The Controller processes the user requests and passes them to the view for rendering.

- Dispatcher Servlet
  ---
Dispatcher Servlet is the front controller that manages the entire HTTP request and response handling process. 
Now, the question is What is Front Controller? It is quite simple, as the name suggests, when any web requests are made, the requests will come first to the front Controller which is nothing but the Dispatcher Servlet. The Front Controller stands first that is why it’s name is like this. After the requests comes into this, the dispatcher servlet accepts the requests and decides which controller will be suitable to handle these requests. Then it dispatches the HTTP requests to specific controller.

- Spring Model-View-Controller Flow Diagram


![Spring-MVC-Framework-Control-flow-Diagram](https://github.com/rhushikesh2000/new/assets/124034778/a8e69888-1245-4103-abb5-837fe2a8001c)


- Spring MVC Framework works as follows:
  ---
All the incoming requests are intercepted by the DispatcherServlet that works as the front controller.
The DispatcherServlet then gets an entry of handler mapping from the XML file and forwards the request to the controller.
The object of ModelAndView is returned by the controller.
The DispatcherServlet checks the entry of the view resolver in the XML file and invokes the appropriate view component.

- Advantages of Spring MVC Framework
  ---
The container is used for the development and deployment of applications and uses a lightweight servlet.

It enables rapid and parallel development.

Development of the application becomes fast.

Easy for multiple developers to work together.

Easier to Update the application.

It is Easier to Debug because we have multiple levels in the application.

- Disadvantages of Spring MVC Framework
  ---
It has high complexity to develop the applications using this pattern.

It is not suitable for small applications which affect the application’s performance and design.

Spring Web MVC Framework Example :
---

Let's see the simple example of a Spring Web MVC framework. The steps are as follows:


1. Load the spring jar files or add dependencies in the case of Maven
2. Create the controller class
3. Provide the entry of controller in the web.xml file
4. Define the bean in the separate XML file
5. Display the message in the JSP page
6. Start the server and deploy the project

Directory Structure of Spring MVC using Maven


![directory-structure-of-spring-mvc-using-maven](https://github.com/rhushikesh2000/new/assets/124034778/a5039908-faf2-4c13-bf14-d9c66797655f)


1. Provide project information and configuration in the pom.xml file.

- pom.xml

~~~java
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">  
  <modelVersion>4.0.0</modelVersion>  
  <groupId>com.javatpoint</groupId>  
  <artifactId>SpringMVC</artifactId>  
  <packaging>war</packaging>  
  <version>0.0.1-SNAPSHOT</version>  
  <name>SpringMVC Maven Webapp</name>  
  <url>http://maven.apache.org</url>  
  <dependencies>  
    <dependency>  
      <groupId>junit</groupId>  
      <artifactId>junit</artifactId>  
      <version>3.8.1</version>  
      <scope>test</scope>  
    </dependency>  
      
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->  
<dependency>  
    <groupId>org.springframework</groupId>  
    <artifactId>spring-webmvc</artifactId>  
    <version>5.1.1.RELEASE</version>  
</dependency>  
  
<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->  
<dependency>    
    <groupId>javax.servlet</groupId>    
    <artifactId>servlet-api</artifactId>    
    <version>3.0-alpha-1</version>    
</dependency>  
  
  </dependencies>  
  <build>  
    <finalName>SpringMVC</finalName>  
  </build>  
</project>

~~~
2. Create the controller class
   
To create the controller class, we are using two annotations @Controller and @RequestMapping.

The @Controller annotation marks this class as Controller.

The @Requestmapping annotation is used to map the class with the specified URL name.

- HelloController.java

~~~java
package com.springmvc;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
@Controller  
public class HelloController {  
@RequestMapping("/")  
    public String display()  
    {  
        return "index";  
    }     
}

~~~
3. Provide the entry of controller in the web.xml file
   
In this xml file, we are specifying the servlet class DispatcherServlet that acts as the front controller in Spring Web MVC. All the incoming request for the html file will be forwarded to the DispatcherServlet.

- web.xml

~~~java
<?xml version="1.0" encoding="UTF-8"?>  
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">  
  <display-name>SpringMVC</display-name>  
   <servlet>    
    <servlet-name>spring</servlet-name>    
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>    
    <load-on-startup>1</load-on-startup>      
</servlet>    
<servlet-mapping>    
    <servlet-name>spring</servlet-name>    
    <url-pattern>/</url-pattern>    
</servlet-mapping>    
</web-app>

~~~
4. Define the bean in the xml file
   
This is the important configuration file where we need to specify the View components.

The context:component-scan element defines the base-package where DispatcherServlet will search the controller class.

This xml file should be located inside the WEB-INF directory.

- spring-servlet.xml
  
~~~java
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"   
    xmlns:context="http://www.springframework.org/schema/context"  
    xmlns:mvc="http://www.springframework.org/schema/mvc"  
    xsi:schemaLocation="  
        http://www.springframework.org/schema/beans  
        http://www.springframework.org/schema/beans/spring-beans.xsd  
        http://www.springframework.org/schema/context  
        http://www.springframework.org/schema/context/spring-context.xsd  
        http://www.springframework.org/schema/mvc  
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">  
  
    <!-- Provide support for component scanning -->  
    <context:component-scan base-package="com.springmvc" />  
  
    <!--Provide support for conversion, formatting and validation -->  
    <mvc:annotation-driven/>  
  
</beans>

~~~
5. Display the message in the JSP page
   
This is the simple JSP page, displaying the message returned by the Controller.

- index.jsp
~~~java
<html>  
<body>  
<p>Welcome to Spring MVC Tutorial</p>  
</body>  
</html>
~~~
Output:



![spring-mvc-tutorial](https://github.com/rhushikesh2000/new/assets/124034778/84a0c1fe-cf65-48a4-aa2c-405f98395f14)



