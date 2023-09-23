package task3;

import java.util.Scanner;

public class Main {
	
		 static  class Rectangle {
		  // Instance variables to store the length and width of the rectangle
		  int length;
		  int width;

		  // A constructor to initialize the length and width with the user entered values
		  Rectangle(int l, int w) {
		   this. length = l;
		   this. width = w;
		  }

		  // A method to calculate and return the area of the rectangle
		  int area() {
		    return length * width;
		  }
		}

		// A class to test the Rectangle class
		  public static void main(String[] args) {
		    // Create a Scanner object to read user input
		    Scanner sc = new Scanner(System.in);

		    // Prompt the user to enter the length and width of the rectangle
		    System.out.println("Enter the length of the rectangle:");
		    int l = sc.nextInt();
		    System.out.println("Enter the width of the rectangle:");
		    int w = sc.nextInt();

		   // Main main=new Main();
		    // Create a Rectangle object with the user entered values
		    
		    Rectangle r = new Rectangle(l, w);

		    // Print the area of the rectangle
		    System.out.println("The area of the rectangle is: " + r.area());
		  }
		}
		


