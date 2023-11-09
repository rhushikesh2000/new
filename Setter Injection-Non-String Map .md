## Setter Injection with Non-String Map (having dependent Object) Example

In this example, we are using map as the answer that have Answer and User. Here, we are using key and value pair both as an object. Answer has its own information such as answerId, answer and postedDate, User has its own information such as userId, username, emailId.

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
private Map<Answer,User> answers;  
  
//getters and setters  
  
  
public void displayInfo(){  
    System.out.println("question id:"+id);  
    System.out.println("question name:"+name);  
    System.out.println("Answers....");  
    Set<Entry<Answer, User>> set=answers.entrySet();  
    Iterator<Entry<Answer, User>> itr=set.iterator();  
    while(itr.hasNext()){  
        Entry<Answer, User> entry=itr.next();  
        Answer ans=entry.getKey();  
        User user=entry.getValue();  
        System.out.println("Answer Information:");  
        System.out.println(ans);  
        System.out.println("Posted By:");  
        System.out.println(user);  
    }  
}  
}
~~~
 
- Answer.java

~~~java
package com.setter;  
  
import java.util.Date;  
  
public class Answer {  
private int id;  
private String answer;  
private Date postedDate;  
public Answer() {}  
public Answer(int id, String answer, Date postedDate) {  
    super();  
    this.id = id;  
    this.answer = answer;  
    this.postedDate = postedDate;  
}  
  
public String toString(){  
    return "Id:"+id+" Answer:"+answer+" Posted Date:"+postedDate;  
}  
} 
~~~

- User.java

~~~java
package com.setter;  
  
public class User {  
private int id;  
private String name,email;  
public User() {}  
public User(int id, String name, String email) {  
    super();  
    this.id = id;  
    this.name = name;  
    this.email = email;  
}  
  
public String toString(){  
    return "Id:"+id+" Name:"+name+" Email Id:"+email;  
}  
} 

~~~

- applicationContext.xml

The key-ref and value-ref attributes of entry element is used to define the reference of bean in the map.

~~~java
<?xml version="1.0" encoding="UTF-8"?>  
<beans  
    xmlns="http://www.springframework.org/schema/beans"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xmlns:p="http://www.springframework.org/schema/p"  
    xsi:schemaLocation="http://www.springframework.org/schema/beans   
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">  
  
<bean id="answer1" class="com.setter.Answer">  
<property name="id" value="1"></property>  
<property name="answer" value="Java is a Programming Language"></property>  
<property name="postedDate" value="12/12/2001"></property>  
</bean>  
<bean id="answer2" class="com.setter.Answer">  
<property name="id" value="2"></property>  
<property name="answer" value="Java is a Platform"></property>  
<property name="postedDate" value="12/12/2003"></property>  
</bean>  
  
<bean id="user1" class="com.setter.User">  
<property name="id" value="1"></property>  
<property name="name" value="Arun Kumar"></property>  
<property name="email" value="arun@gmail.com"></property>  
</bean>  
<bean id="user2" class="com.setter.User">  
<property name="id" value="2"></property>  
<property name="name" value="Varun Kumar"></property>  
<property name="email" value="Varun@gmail.com"></property>  
</bean>  
  
<bean id="q" class="com.setter.Question">  
<property name="id" value="1"></property>  
<property name="name" value="What is Java?"></property>  
<property name="answers">  
<map>  
<entry key-ref="answer1" value-ref="user1"></entry>  
<entry key-ref="answer2" value-ref="user2"></entry>  
</map>  
</property>  
</bean>  
  
</beans>
~~~
- Test.java
  
This class gets the bean from the applicationContext.xml file and calls the displayInfo() method to display the information.

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
