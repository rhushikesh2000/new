## ViewResolver in Spring MVC

Welcome to this comprehensive guide on utilizing the ViewResolver interface in Spring MVC. As a core component of Spring's MVC framework, ViewResolver plays a pivotal role in mapping view names to actual views. Whether you're new to Spring MVC or looking to refresh your understanding, 
this article aims to provide a clear and detailed examination of ViewResolver

- Understanding ViewResolver in Spring MVC
  ---
In the context of Spring MVC, the ViewResolver interface assists in mapping logical view names, returned by the Controller, to actual view objects, such as JSPs or Thymeleaf templates. It forms a bridge between the controller and the view component of the MVC architecture, determining which view to display based on the logical view name

- The Role of ViewResolver in the Spring MVC Flow
  ---
When a request is processed by a Spring MVC application, the request is received by the DispatcherServlet, which then routes it to the appropriate controller. The controller performs the required business logic and returns a ModelAndView object, containing the model data and the logical name of the view

This logical view name is passed onto the ViewResolver, which then resolves it to a specific view technology, such as JSP, Thymeleaf, or FreeMarker. The DispatcherServlet will render this view, along with the model data, back to the client.

Spring MVC - Internal Resource View Resolver Example :

The InternalResourceViewResolver is used to resolve the provided URI to actual URI. The following example shows how to use the InternalResourceViewResolver using the Spring Web MVC Framework. The InternalResourceViewResolver allows mapping webpages with requests.

~~~java
package com.ViewResolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/hello")
public class HelloController{
 
   @RequestMapping(method = RequestMethod.GET)
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");

      return "hello";
   }
}

~~~

~~~java
<bean class = "org.springframework.web.servlet.view.InternalResourceViewResolver">
   <property name = "prefix" value = "/WEB-INF/jsp/"/>
   <property name = "suffix" value = ".jsp"/>
</bean>

~~~

For example, using the above configuration, if URI

~~~java
/hello is requested, DispatcherServlet will forward the request to the prefix + viewname + suffix = /WEB-INF/jsp/hello.jsp.

~~~

To start with, let us have a working Eclipse IDE in place and then consider the following steps to develop a Dynamic Form based Web Application using the Spring Web Framework.

- Step	Description
  ---
1.	Create a project with a name TestWeb under a package com.tutorialspointas explained in the Spring MVC - Hello World Example chapter.
2.	Create a Java classes HelloController under the com.ViewResolverpackage.
3.	Create a view file hello.jsp under jsp sub-folder.
4.	The final step is to create the content of the source and configuration files and export the application as explained below.
   
- HelloController.java
~~~java
package com.ViewResolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/hello")
public class HelloController{
 
   @RequestMapping(method = RequestMethod.GET)
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");

      return "hello";
   }

}
~~~

- TestWeb-servlet.xml
~~~java
<beans xmlns = "http://www.springframework.org/schema/beans"
   xmlns:context = "http://www.springframework.org/schema/context"
   xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation = "
   http://www.springframework.org/schema/beans     
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
   http://www.springframework.org/schema/context 
   http://www.springframework.org/schema/context/spring-context-3.0.xsd">

   <context:component-scan base-package = "com.ViewResolver" />

   <bean class = "org.springframework.web.servlet.view.InternalResourceViewResolver">
      <property name = "prefix" value = "/WEB-INF/jsp/" />
      <property name = "suffix" value = ".jsp" />
   </bean>
 
</beans>

~~~
- hello.jsp
~~~java
<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <title>Hello World</title>
   </head>
   <body>
      <h2>${message}</h2>
   </body>
</html>

~~~
Once you are done with creating source and configuration files, export your application. Right click on your application, use Export → WAR File option and save the TestWeb.war file in Tomcat's webapps folder.

Now, start your Tomcat server and make sure you are able to access other webpages from the webapps folder using a standard browser. Try to access the URL – http://localhost:8080/TestWeb/hello and if everything is fine with the Spring Web Application, we will see the following screen.



![spring-mvc-tutorial](https://github.com/rhushikesh2000/new/assets/124034778/14c23e8f-3d8a-498c-bcd6-10495ea009f1)

