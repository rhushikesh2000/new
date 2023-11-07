## Spring – Setter Injection with Collection

Dependency Injection is the main functionality provided by Spring IOC(Inversion of Control). The Spring-Core module is responsible for injecting dependencies through either Constructor or Setter methods. In Setter Dependency Injection(SDI) the dependency will be injected with the help of setters and getters methods. A bean-configuration file is used to set DI as SDI in the bean. For this, the property to be set with the SDI is declared under the <property> tag in the bean-config file.

A Collection in java is a group of individual objects. Spring framework provides us facility of Setter injection using the following Collections:

- List
- Map
- Set

Each collection can have string based and non-string based values.

In this example, we are taking the example of Forum where One question can have multiple answers. There are three pages:

- Question.java
- applicationContext.xml
- Test.java
In this example, we are using list that can have duplicate elements, you may use set that have only unique elements. But, you need to change list to set in the applicationContext.xml file and List to Set in the Question.java file.

Question.java
This class contains three properties with setters and getters and displayInfo() method that prints the information. Here, we are using List to contain the multiple answers.

- Question.java

~~~java
package com.setter;  
import java.util.Iterator;  
import java.util.List;  
  
public class Question {  
private int id;  
private String name;  
private List<String> answers;  
  
//setters and getters  
  
public void displayInfo(){  
    System.out.println(id+" "+name);  
    System.out.println("answers are:");  
    Iterator<String> itr=answers.iterator();  
    while(itr.hasNext()){  
        System.out.println(itr.next());  
    }  
}  
  
}  
~~~

- applicationContext.xml
  
We will use property element for setter injection. The name element of the property attribute will be equal to the variable name and the value element will contain the value you want to assign to that variable.

  ~~~java
<?xml version="1.0" encoding="UTF-8"?>  
<beans  
    xmlns="http://www.springframework.org/schema/beans"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xmlns:p="http://www.springframework.org/schema/p"  
    xsi:schemaLocation="http://www.springframework.org/schema/beans   
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">  
  
<bean id="q" class="com.setter.Question">  
<property name="id" value="1"></property>  
<property name="name" value="What is Java?"></property>  
<property name="answers">  
<list>  
<value>Java is a programming language</value>  
<value>Java is a platform</value>  
<value>Java is an Island</value>  
</list>  
</property>  
</bean>  
  
</beans>  
  ~~~

- Test.java

This class gets the bean from the applicationContext.xml file and calls the displayInfo method.

~~~java
package com.setter;  
  
import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.core.io.ClassPathResource;  
import org.springframework.core.io.Resource;  
  
public class Test {  
public static void main(String[] args) {  
    Resource r=new ClassPathResource("applicationContext.xml");  
    BeanFactory factory=new XmlBeanFactory(r);  
      
    Question q=(Question)factory.getBean("q");  
    q.displayInfo();  
      
}  
}  
~~~
