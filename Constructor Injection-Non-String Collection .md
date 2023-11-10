## Constructor Injection with Non-String Collection (having Dependent Object) 

If we have dependent object in the collection, we can inject these information by using the ref element inside the list, set or map.

In this example, we are taking the example of Forum where One question can have multiple answers. But Answer has its own information such as answerId, answer and postedBy. There are four pages used in this example:

- Question.java
- Answer.java
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
private List<Answer> answers;  
  
public Question() {}  
public Question(int id, String name, List<Answer> answers) {  
    super();  
    this.id = id;  
    this.name = name;  
    this.answers = answers;  
}  
  
public void displayInfo(){  
    System.out.println(id+" "+name);  
    System.out.println("answers are:");  
    Iterator<Answer> itr=answers.iterator();  
    while(itr.hasNext()){  
        System.out.println(itr.next());  
    }  
}  
  
}
~~~

- Answer.java
  
This class has three properties id, name and by with constructor and toString() method.

~~~java
package com.constructor;  
  
public class Answer {  
private int id;  
private String name;  
private String by;  
  
public Answer() {}  
public Answer(int id, String name, String by) {  
    super();  
    this.id = id;  
    this.name = name;  
    this.by = by;  
}  
  
public String toString(){  
    return id+" "+name+" "+by;  
}  
}

~~~

- applicationContext.xml
  
The ref element is used to define the reference of another bean. Here, we are using bean attribute of ref element to specify the reference of another bean.

~~~java
<?xml version="1.0" encoding="UTF-8"?>  
<beans  
    xmlns="http://www.springframework.org/schema/beans"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xmlns:p="http://www.springframework.org/schema/p"  
    xsi:schemaLocation="http://www.springframework.org/schema/beans   
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">  
  
<bean id="ans1" class="com.constructor.Answer">  
<constructor-arg value="1"></constructor-arg>  
<constructor-arg value="Java is a programming language"></constructor-arg>  
<constructor-arg value="John"></constructor-arg>  
</bean>  
  
<bean id="ans2" class="com.constructor.Answer">  
<constructor-arg value="2"></constructor-arg>  
<constructor-arg value="Java is a Platform"></constructor-arg>  
<constructor-arg value="Ravi"></constructor-arg>  
</bean>  
  
<bean id="q" class="com.constructor.Question">  
<constructor-arg value="111"></constructor-arg>  
<constructor-arg value="What is java?"></constructor-arg>  
<constructor-arg>  
<list>  
<ref bean="ans1"/>  
<ref bean="ans2"/>  
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
