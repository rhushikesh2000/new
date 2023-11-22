## MVC Architecture Layers

- Model Layer
  ---
The Model in the MVC design pattern acts as a data layer for the application. It represents the business logic for application and also the state of application. The model object fetch and store the model state in the database. Using the model layer, rules are applied to the data that represents the concepts of application.

Let's consider the following code snippet that creates a which is also the first step to implement MVC pattern.

Employee.java

~~~java
// class that represents model  
public class Employee {  
  
      // declaring the variables  
       private String EmployeeName;  
       private String EmployeeId;  
       private String EmployeeDepartment;  
          
      // defining getter and setter methods  
       public String getId() {  
          return EmployeeId;  
       }  
          
       public void setId(String id) {  
          this.EmployeeId = id;  
       }  
          
       public String getName() {  
          return EmployeeName;  
       }  
          
       public void setName(String name) {  
          this.EmployeeName = name;  
       }  
          
       public String getDepartment() {  
              return EmployeeDepartment;  
           }  
          
       public void setDepartment(String Department) {  
              this.EmployeeDepartment = Department;  
           }  
          
    }
~~~ 
The above code simply consists of getter and setter methods to the Employee class.

---

- View Layer
  ---
As the name depicts, view represents the visualization of data received from the model. The view layer consists of output of application or user interface. It sends the requested data to the client, that is fetched from model layer by controller.

Let's take an example where we create a view using the EmployeeView class.

EmployeeView.java

~~~java

// class which represents the view  
public class EmployeeView {  
  
      // method to display the Employee details   
public void printEmployeeDetails (String EmployeeName, String EmployeeId, String EmployeeDepartment){  
          System.out.println("Employee Details: ");  
          System.out.println("Name: " + EmployeeName);  
          System.out.println("Employee ID: " + EmployeeId);  
          System.out.println("Employee Department: " + EmployeeDepartment);  
       }  
    }
~~~
- Controller Layer
  ---
The controller layer gets the user requests from the view layer and processes them, with the necessary validations. It acts as an interface between Model and View. The requests are then sent to model for data processing. Once they are processed, the data is sent back to the controller and then displayed on the view.

Let's consider the following code snippet that creates the controller using the EmployeeController class.

EmployeeController.java

~~~java
// class which represent the controller  
public class EmployeeController {  
  
      // declaring the variables model and view  
       private Employee model;  
       private EmployeeView view;  
   
      // constructor to initialize  
       public EmployeeController(Employee model, EmployeeView view) {  
          this.model = model;  
          this.view = view;  
       }  
   
      // getter and setter methods   
       public void setEmployeeName(String name){  
          model.setName(name);        
       }  
   
       public String getEmployeeName(){  
          return model.getName();         
       }  
   
       public void setEmployeeId(String id){  
          model.setId(id);        
       }  
   
       public String getEmployeeId(){  
          return model.getId();       
       }  
   
       public void setEmployeeDepartment(String Department){  
              model.setDepartment(Department);        
       }  
   
           public String getEmployeeDepartment(){  
              return model.getDepartment();         
       }  
  
       // method to update view   
       public void updateView() {                  
          view.printEmployeeDetails(model.getName(), model.getId(), model.getDepartment());  
       }      
    }
~~~

- Main Class Java file
  ---
The following example displays the main file to implement the MVC architecture. Here, we are using the MVCMain class.

MVCMain.java
~~~java
// main class  
public class MVCMain {  
       public static void main(String[] args) {  
   
          // fetching the employee record based on the employee_id from the database  
          Employee model = retriveEmployeeFromDatabase();  
   
          // creating a view to write Employee details on console  
          EmployeeView view = new EmployeeView();  
   
          EmployeeController controller = new EmployeeController(model, view);  
   
          controller.updateView();  
   
          //updating the model data  
          controller.setEmployeeName("Nirnay");  
          System.out.println("\n Employee Details after updating: ");  
   
          controller.updateView();  
       }  
   
       private static Employee retriveEmployeeFromDatabase(){  
          Employee Employee = new Employee();  
          Employee.setName("Anu");  
          Employee.setId("11");  
          Employee.setDepartment("Salesforce");  
          return Employee;  
       }  
    }
~~~
The MVCMain class fetches the employee data from the method where we have entered the values. Then it pushes those values in the model. After that, it initializes the view (EmployeeView.java). When view is initialized, the Controller (EmployeeController.java) is invoked and bind it to Employee class and EmployeeView class. At last the updateView() method (method of controller) update the employee details to be printed to the console.


Output:

~~~java
Employee Details:
Name: Anu          
Employee ID: 11
Employee Department: Salesforce

Employee Details after updating:
Name: Nirnay          
Employee ID: 11
Employee Department: Salesforce
~~~

In this way, we have learned about MVC Architecture, significance of each layer and its implementation in Java.
