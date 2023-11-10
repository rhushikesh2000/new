## Constructor Injection with Collection Example

We can inject collection values by constructor in spring framework. There can be used three elements inside the constructor-arg element.

It can be:
- list
- set
- map

Each collection can have string based and non-string based values.

In this example, we are taking the example of Forum where One question can have multiple answers. There are three pages:

- Question.java
- applicationContext.xml
- Test.java
  
In this example, we are using list that can have duplicate elements, you may use set that have only unique elements. But, you need to change list to set in the applicationContext.xml file and List to Set in the Question.java file.

- Question.java
  
This class contains three properties, two constructors and displayInfo() method that prints the information. Here, we are using List to contain the multiple answers.

~~~java
package com.constructor;  
  
import java.util.Iterator;  
import java.util.List;  
  
public class Question {  
private int id;  
private String name;  
private List<String> answers;  
  
public Question() {}  
public Question(int id, String name, List<String> answers) {  
    super();  
    this.id = id;  
    this.name = name;  
    this.answers = answers;  
}  
  
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
  
The list element of constructor-arg is used here to define the list.

~~~java
<?xml version="1.0" encoding="UTF-8"?>  
<beans  
    xmlns="http://www.springframework.org/schema/beans"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xmlns:p="http://www.springframework.org/schema/p"  
    xsi:schemaLocation="http://www.springframework.org/schema/beans  
 http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">  
  
<bean id="q" class="com.constructor.Question">  
<constructor-arg value="111"></constructor-arg>  
<constructor-arg value="What is java?"></constructor-arg>  
<constructor-arg>  
<list>  
<value>Java is a programming language</value>  
<value>Java is a Platform</value>  
<value>Java is an Island of Indonasia</value>  
</list>  
</constructor-arg>  
</bean>  
  
</beans>

~~~

- Test.java
  
This class gets the bean from the applicationContext.xml file and calls the displayInfo method.

~~~java
package com.constructor;  
  
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
