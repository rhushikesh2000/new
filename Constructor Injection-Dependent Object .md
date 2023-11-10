## Constructor Injection with Dependent Object

In Constructor Injection, the Dependency Injection will be injected with the help of constructors. Now to set the Dependency Injection as Constructor Dependency Injection in bean, it is done through the bean-configuration file. 
For this, the property to be set with the CDI is declared under the <constructor-arg> tag in the bean-config file.
So in this article, let’s learn how we are going to use Spring to inject our dependencies into our object values by Setter Injection. 
Object is a basic unit of Object-Oriented Programming and represents real-life entities. So generally in Java, we create objects of a class using the new keyword.

~~~java
Test t = new Test();
// creating object of class Test
// t is the object
~~~

- Implementation: 

Suppose we have a class named “Student” and inside the class, we want to inject the object of another class named “MathCheat”. And there is one method also present inside the class named “cheating()” something like that.

- Student.java

~~~java
public class Student 
{
    // Class data members
    private int id;
    private MathCheat mathCheat;

    // Method
    public void cheating() 
    {

        System.out.println("My ID is: " + id);

        mathCheat.mathCheating();
    }

}

~~~

- MathCheat.java

~~~java

public class MathCheat 
{
    public void mathCheating() 
    {
        System.out.println("And I Have Stated Math Cheating");
    }
}
~~~
So now we want to inject the object of MathCheat into the Student class by using the concept of Constructor Dependency Injection. So at first, we have to create the constructor inside the Student.java file.
Now the Student.java file is something like this.

~~~java
// Java Program to Illustrate Student class
 
public class Student {
 
    // Class data members
    private int id;
    private MathCheat mathCheat;
 
    // Constructor of Student class
    public Student(int id, MathCheat mathCheat)
    {
        this.id = id;
        this.mathCheat = mathCheat;
    }
 
    // Method
    public void cheating()
    {
        System.out.println("My ID is: " + id);
        mathCheat.mathCheating();
    }
}
 ~~~
 

The only thing we need to change is in the beans.xml file. Now let’s create a Student Bean in the beans.xml file and inside the bean, you have to add your property’s 
name and its corresponding values inside the constructor-arg tag instead of the property tag, like this

 
- Syntax: Standard 

~~~java
<bean id="AnyUniqueId" class="YourClassName">
  <constructor-arg name="attributes that you have defined in your class" value="And its corresponding values"/>
</bean>

~~~

For example: For this project, we can write something like this

~~~java
<bean id="student" class="Student">
    <constructor-arg name="id" value="101"/>
    <constructor-arg name="mathCheat">
       <bean class="MathCheat"></bean>
    </constructor-arg>
</bean>
 
~~~

You can see how we created the bean of MathCheat class inside the Student bean. Below is the complete code for the beans.xml file

~~~java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd">
 
    <bean id="student" class="Student">
        <constructor-arg name="id" value="101"/>
        <constructor-arg name="mathCheat">
            <bean class="MathCheat"></bean>
        </constructor-arg>
    </bean>
 
</beans>
 
 ~~~

So for testing this stuff let’s create a main method and call the cheating() method inside the Student class. Below is the code for the Main.java file.


- Application File
  
~~~java
// Java Program to Illustrate Application Class
 
// Importing required classes
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
// Application class
public class Main {
 
    // Main driver method
    public static void main(String[] args)
    {
 
        // Implementing Spring IoC
        // Using ApplicationContext
        ApplicationContext context
            = new ClassPathXmlApplicationContext(
                "beans.xml");
 
        // Getting the bean student
        Student student
            = context.getBean("student", Student.class);
 
        // Calling the method inside main() method
        student.cheating();
    }
}

~~~

Output:
~~~

My ID is: 101
And I Have Stated Math Cheating

~~~

-  Another Approach (Right Approach)
  ---
 

So there is another approach to create the bean of  MathCheat class inside the beans.xml file. So you can write something like this using the “ref“. 

 ~~~java

<bean id="mathCheatObjectValue" class="MathCheat"></bean>


<bean id="student" class="Student">
    <constructor-arg name="id" value="101"/>
    <constructor-arg name="mathCheat" ref="mathCheatObjectValue"/>
</bean>

~~~

Below is the complete code for the beans.xml file.

 
- Example:

 ~~~java

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd">
     
    <bean id="mathCheatObjectValue" class="MathCheat"></bean>
 
    <bean id="student" class="Student">
         <constructor-arg name="id" value="101"/>
         <constructor-arg name="mathCheat" ref="mathCheatObjectValue"/>
    </bean>
 
</beans>

~~~
 

So whenever you are going to run your application you are going to get the same output. Example
