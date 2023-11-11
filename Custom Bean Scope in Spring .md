## Custom Bean Scope in Spring

In this article, we are going to discuss the custom bean scope in Spring. I presume that you guys know about Spring bean and its scopes provided by Spring framework. A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container. Spring provides two standard bean scopes i.e. singleton and prototype that we can use in any Spring application. Apart from these two scopes, Spring provides three additional bean scopes i.e. request, session, globalSession which can be used only in web-aware applications.

We can’t override/modify the standard bean scopes of Spring i.e. singleton and prototype. It’s generally considered a bad practice to override the web-aware scopes. But sometimes application demands something out of box or additional capabilities from those found in the provided scopes. As of Spring 2.0, we can define custom spring bean scopes as well as modify existing spring bean scopes (except singleton and prototype scopes).

Let’s try to understand the requirement of custom bean scope with the help of an example. Suppose, if you are developing a multi-tenant system, you may want to provide a separate instance of a particular bean or set of beans for each tenant. Spring provides a mechanism for creating custom scopes for scenarios such as this. So let’s see how to create custom spring bean scope with an example.


- To integrate your custom scope(s) into the Spring container, you need to implement the org.springframework.beans.factory.config.Scope interface.
This Scope interface contains four methods,

1. Object get(String name, ObjectFactory objectFactory)
2. Object remove(String name)
3. void registerDestructionCallback(String name, Runnable destructionCallback)
4. String getConversationId()
   
To implement custom bean scope we will perform a few steps one by one like the below:

- Step 1. Create Custom Bean Scope Class
~~~java
package com.scripter;
 
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
 
public class CustomThreadScope implements Scope {
    CustomThreadLocal customThreadLocal = new CustomThreadLocal();
   
    @Override
    public Object get(String str, ObjectFactory objectFactory) {
        System.out.println("Fetched object from scope");
       
        @SuppressWarnings("unchecked")
        Map<String, Object> scope = (Map<String, Object>) customThreadLocal.get();
        Object object = scope.get(str);
        if (object == null) {
            object = objectFactory.getObject();
            scope.put(str, object);
        }
       
        return object;
    }
   
    @Override
    public String getConversationId() {
        return null;
    }
   
    @Override
    public void registerDestructionCallback(String arg0, Runnable arg1) {
    }
   
    @Override
    public Object remove(String str) {
        Map<String, Object> scope = (Map<String, Object>) customThreadLocal.get();
        return scope.remove(str);
    }
   
    @Override
    public Object resolveContextualObject(String arg0) {
        return null;
    }
   
    class CustomThreadLocal extends ThreadLocal {
        protected Map<String, Object> initialValue() {
            System.out.println("Initializing ThreadLocal");
            return new HashMap<String, Object>();
        }
    }
}
~~~
- Step 2. Register Created Custom Bean Scope

In the above step, we have created a Thread bean scope class. Now we have to register this bean to the Spring application context.
Then we will get the benefit of the newly created scope class in the application. Here there are two ways through which we can register custom scope i.e. programmatic registration or we can do it via using XML-based configuration. In this tutorial, we will go with programmatic registration :

~~~java
package com.scripter;
 
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
 
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        Scope scope = new CustomThreadScope();
        applicationContext.getBeanFactory().registerScope("threadScope", scope);
    }
}
~~~
- Step 3. Using the custom scope
  
~~~java
package com.scripter;
 
public class Volunteer {
    private String name;
    private String qualification;
    private String address;
    private String dob;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getQualification() {
        return qualification;
    }
 
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getDob() {
        return dob;
    }
 
    public void setDob(String dob) {
        this.dob = dob;
    }
}

~~~

~~~java
package com.scripter;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
 
@Configuration
@ComponentScan(basePackages = { "com.scripter" })
public class AppConfig {
 
    @Bean
    @Scope("threadScope")
    public Volunteer getVolunteer() {
        Volunteer volunteer = new Volunteer();
        volunteer.setName("Bikash Dubey");
        volunteer.setQualification("B.Tech - CSE");
        volunteer.setDob("07-03-1997");
        volunteer.setAddress("Dibrugarh, Assam");
 
        return volunteer;
    }
}
~~~

- Step 4. Testing of Custom Scope

~~~java
package com.scripter;
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
 
public class App {
    static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
 
    public static void main(String[] args) {
         
        // This code will be executed by child thread
        Runnable childThread = () -> {
            Volunteer v1 = context.getBean(Volunteer.class);
            Volunteer v2 = context.getBean(Volunteer.class);
            System.out.println("Hashcode of two object created by child thread");
            System.out.println(v1.hashCode() + " & " + v2.hashCode());
            System.out.println("Is both objects created by child thread same ? :" + (v1.hashCode() == v2.hashCode()));
        };
        new Thread(childThread).start();
 
        // This code will be executed by main thread
        Volunteer v1 = context.getBean(Volunteer.class);
        Volunteer v2 = context.getBean(Volunteer.class);
        System.out.println("Hashcode of two object created by main thread");
        System.out.println(v1.hashCode() + " & " + v2.hashCode());
        System.out.println("Is both objects created by main thread same ? :" + (v1.hashCode() == v2.hashCode()));
     
    }
}

~~~
Output:
~~~java
Hashcode of two object created by child thread
1522311648 & 1522311648
Is both objects created by child thread same ? : true
Hashcode of two object created by main thread
400197113 & 400197113
Is both objects created by main thread same ? : true
~~~

As you can see, Here the scope of the bean is thread i.e. for each thread one bean will be created as per our custom thread scope bean. 
Do you know something, Spring 3.0 comes with a thread scope class name is SimpleThreadScope, which we will learn in the next tutorial.

