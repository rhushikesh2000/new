## Constructor Injection with Map Example

In this example, we are using map as the answer that have answer with posted username. Here, we are using key and value pair both as a string.

Like previous examples, it is the example of forum where one question can have multiple answers.

- Question.java

This class contains three properties, two constructors and displayInfo() method to display the information.

~~~java
package com.construction;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Set;  
import java.util.Map.Entry;  
  
public class Question {  
private int id;  
private String name;  
private Map<String,String> answers;  
  
public Question() {}  
public Question(int id, String name, Map<String, String> answers) {  
    super();  
    this.id = id;  
    this.name = name;  
    this.answers = answers;  
}  
  
public void displayInfo(){  
    System.out.println("question id:"+id);  
    System.out.println("question name:"+name);  
    System.out.println("Answers....");  
    Set<Entry<String, String>> set=answers.entrySet();  
    Iterator<Entry<String, String>> itr=set.iterator();  
    while(itr.hasNext()){  
        Entry<String,String> entry=itr.next();  
        System.out.println("Answer:"+entry.getKey()+" Posted By:"+entry.getValue());  
    }  
}  
}  
~~~
- applicationContext.xml

The entry attribute of map is used to define the key and value information.

~~~java
<?xml version="1.0" encoding="UTF-8"?>  
<beans  
    xmlns="http://www.springframework.org/schema/beans"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xmlns:p="http://www.springframework.org/schema/p"  
    xsi:schemaLocation="http://www.springframework.org/schema/beans   
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">  
  
<bean id="q" class="com.construction.Question">  
<constructor-arg value="11"></constructor-arg>  
<constructor-arg value="What is Java?"></constructor-arg>  
<constructor-arg>  
<map>  
<entry key="Java is a Programming Language"  value="Ajay Kumar"></entry>  
<entry key="Java is a Platform" value="John Smith"></entry>  
<entry key="Java is an Island" value="Raj Kumar"></entry>  
</map>  
</constructor-arg>  
</bean>  
  
</beans>
~~~

- Test.java
  
This class gets the bean from the applicationContext.xml file and calls the displayInfo() method.

~~~java
package com.construction;  
  
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


