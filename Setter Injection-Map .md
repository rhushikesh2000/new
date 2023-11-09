## Setter Injection with Map Example

In this example, we are using map as the answer for a question that have answer as the key and username as the value. Here, we are using key and value pair both as a string.

Like previous examples, it is the example of forum where one question can have multiple answers.

- Question.java
  
This class contains three properties, getters & setters and displayInfo() method to display the information.

~~~java
package com.setter;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Set;  
import java.util.Map.Entry;  
  
public class Question {  
private int id;  
private String name;  
private Map<String,String> answers;  
  
//getters and setters  
  
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
  
<bean id="q" class="com.setter.Question">  
<property name="id" value="1"></property>  
<property name="name" value="What is Java?"></property>  
<property name="answers">  
<map>  
<entry key="Java is a programming language"  value="Sonoo Jaiswal"></entry>  
<entry key="Java is a Platform" value="Sachin Yadav"></entry>  
</map>  
</property>  
</bean>  
  
</beans>

~~~

- Test.java
  
This class gets the bean from the applicationContext.xml file and calls the displayInfo() method.

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
