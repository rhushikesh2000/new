## What is Dispatcher Servlet in Spring?

Spring is one of the most popular Java EE frameworks. It is an open-source lightweight framework that allows Java EE 7 developers to build simple, reliable, and scalable enterprise applications. This framework mainly focuses on providing various ways to help you manage your business objects. It made the development of Web applications much easier than compared to classic Java frameworks and application programming interfaces (APIs), such as Java database connectivity (JDBC), JavaServer Pages(JSP), and Java Servlet. This framework uses various new techniques such as Aspect-Oriented Programming (AOP), Plain Old Java Object (POJO), and dependency injection (DI), to develop enterprise applications. Now we are going to discuss one of the most important concepts in the Spring framework e.g Dispatcher Servlet.

- Dispatcher Servlet
  ---
DispatcherServlet acts as the Front Controller for Spring-based web applications. So now what is Front Controller? So it is pretty simple. Any request is going to come into our website the front controller is going to stand in front and is going to accept all the requests and once the front controller accepts that request then this is the job of the front controller that it will make a decision that who is the right controller to handle that request. For example, refer to the below image. Suppose we have a website called student.com and the client is make a request to save student data by hitting the following URL student.com/save and its first come to the front controller and once the front controller accepts that request it is going to assign to the Controller_1 as this controller handle the request for /save operation. Then it is going to return back the response to the Client. 



![DispatcherServlet](https://github.com/rhushikesh2000/new/assets/124034778/7f35dc2b-01bc-49f9-a71b-8747e61b9126)

~~~
DispatcherServlet handles an incoming HttpRequest, delegates the request, and processes that request according to the configured HandlerAdapter 
interfaces that have been implemented within the Spring application along with accompanying annotations specifying handlers,
controller endpoints, and response objects.
~~~

- Setting Up Dispatcher Servlet
  ---

Now let’s see how can we set up DispatcherServlet in our Spring MVC project.  

Step 1: Create a Dynamic Web Project in your STS IDE. You may refer to this article to create a Dynamic Web Project in STS

Step 2: Download the spring JARs file from this link and go to the src > main > webapp > WEB-INF > lib folder and past these JAR files. 

Step 3: Refer to this article Configuration of Apache Tomcat Server and configure the tomcat server with your application. Now we are ready to go.

Step 4: Now go to the src > main > webapp > WEB-INF > web.xml file and here we have to configure our front controller inside a <servlet>…</servlet> tag something like this. 

~~~java
<servlet>

      <!-- Provide a Servlet Name -->
    <servlet-name>frontcontroller-dispatcher</servlet-name>
    
    <!-- Provide a fully qualified path to the DispatcherServlet class -->
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    

</servlet>

~~~

Now let’s tell this servlet, hey servlet you have to handle all the requests coming to our website called student.com (for this example). So the way we are going to tell the above servlet is we can write something like this

~~~java
<servlet-mapping>

      <!-- Provide a Servlet Name that you want to map -->
    <servlet-name>frontcontroller-dispatcher</servlet-name>
    
    <!-- Provide a url pattern -->
    <url-pattern>/student.com/*</url-pattern>
    
</servlet-mapping>
~~~

So this does mean that the servlet “frontcontroller-dispatcher” is going to handle all the requests starting from student.com/anything, that may be student.com/save or student.com/get, anything. But it must start with student.com. So we are done with creating a Dispatcher Servlet. 

- When Dispatcher Servlet will be Initialized?
  ---

The Dispatcher Servlet will be Initialized once we deploy the created dynamic web application inside the tomcat server. So before deploying it let’s add the following line inside the web.xml file 
~~~java
<load-on-startup>1</load-on-startup>
So now the modified code for the servlet is 

<servlet>

      <!-- Provide a Servlet Name -->
    <servlet-name>frontcontroller-dispatcher</servlet-name>
    
    <!-- Provide a fully qualified path to the DispatcherServlet class -->
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    
    <load-on-startup>1</load-on-startup>
    
</servlet>
~~~
Why this line “<load-on-startup>1</load-on-startup>”?

This will make sure that whenever your server will get started the DispatcherServlet will get initialized. And if you are not writing this line of code then whenever the first request will come to your server starting from /student.com at that time only the DispatcherServlet will be initialized and we don’t want it. We want the DispatcherServlet will be initialized during the time of the server startup. That’s why we have written this line of code. The complete web.xml file is given below:

- Example: File: web.xml 

~~~java
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
                             http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
   
  <display-name>myfirst-mvc-project</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.jsp</welcome-file>
    <welcome-file>default.htm</welcome-file>
  </welcome-file-list>
   
  <absolute-ordering/>
   
  <servlet>
      <!-- Provide a Servlet Name -->
    <servlet-name>frontcontroller-dispatcher</servlet-name>
    <!-- Provide a fully qualified path to the DispatcherServlet class -->
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
   
  <servlet-mapping>
      <!-- Provide a Servlet Name that you want to map -->
    <servlet-name>frontcontroller-dispatcher</servlet-name>
    <!-- Provide a url pattern -->
    <url-pattern>/student.com/*</url-pattern>
  </servlet-mapping>
   
</web-app>
~~~

Step 5: Now go to the  src > main > webapp > WEB-INF and create an XML file. Actually, this is a Spring Configuration file like beans.xml file. And the name of the file must be in this format

- YourServletName-servlet.xml
  
For example, for this project, the name of the file must be 

- frontcontroller-dispatcher-servlet.xml
  
So either you can create a Spring Configuration File or you can just create a simple XML file add the below lines of code inside that file. So the code for the frontcontroller-dispatcher-servlet.xml is given below. 

Example: frontcontroller-dispatcher-servlet.xml 
~~~java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
 
</beans>
~~~

Now run your application

Now in the Console, you can see our DispatcherServlet has successfully initialized and also initialization completed without any exceptions or errors.


<img width="781" alt="dispatcher-servelet-2" src="https://github.com/rhushikesh2000/new/assets/124034778/3921e185-9272-4c1c-a8ad-8b4e4602d910">



