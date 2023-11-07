## Setter Injection with Dependent Object  :

Dependency Injection is the main functionality provided by Spring IOC(Inversion of Control). The Spring-Core module is responsible for injecting dependencies through either Constructor or Setter methods.

In Setter Dependency Injection(SDI) the dependency will be injected with the help of setters and getters methods. A bean-configuration file is used to set DI as SDI in the bean. For this, the property to be set with the SDI is declared under the <property> tag in the bean-config file.

- Setter Injection with Dependent Object Example :
  ---

Like Constructor Injection, we can inject the dependency of another bean using setters. In such case, we use property element. Here, our scenario is Employee HAS-A Address. The Address class object will be termed as the dependent object. 

Let's see the Address class first:

- Address.java
  
This class contains four properties, setters and getters and toString() method.

~~~java

package com.setter;  
  
public class Address {  
private String addressLine1,city,state,country;  
  
//getters and setters  
  
public String toString(){  
    return addressLine1+" "+city+" "+state+" "+country;  
}

~~~
- Employee.java

~~~java
It contains three properties id, name and address(dependent object) , setters and getters with displayInfo() method.

package com.setter;  
  
public class Employee {  
private int id;  
private String name;  
private Address address;  
  
//setters and getters  
  
void displayInfo(){  
    System.out.println(id+" "+name);  
    System.out.println(address);  
}  
}
~~~

- applicationContext.xml
  
We will use **ref** attribute of **property** element for specifying the reference of the address bean in the employee bean.

~~~java
<?xml version="1.0" encoding="UTF-8"?>  
<beans  
    xmlns="http://www.springframework.org/schema/beans"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xmlns:p="http://www.springframework.org/schema/p"  
    xsi:schemaLocation="http://www.springframework.org/schema/beans   
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">  
  
<bean id="address1" class="com.setter.Address">  
<property name="addressLine1" value="51,Lohianagar"></property>  
<property name="city" value="Ghaziabad"></property>  
<property name="state" value="UP"></property>  
<property name="country" value="India"></property>  
</bean>  
  
<bean id="obj" class="com.setter.Employee">  
<property name="id" value="1"></property>  
<property name="name" value="Sachin Yadav"></property>  
<property name="address" ref="address1"></property>  
</bean>  
  
</beans>

~~~

- Test.java
  
This class gets the bean from the applicationContext.xml file and calls the displayInfo() method.

~~~java
package com.setter;  
  
import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
import org.springframework.core.io.ClassPathResource;  
import org.springframework.core.io.Resource;  
  
public class Test {  
public static void main(String[] args) {  
    Resource r=new ClassPathResource("applicationContext.xml");  
    BeanFactory factory=new XmlBeanFactory(r);  
      
    Employee e=(Employee)factory.getBean("obj");  
    e.displayInfo();  
      
}  
}  
