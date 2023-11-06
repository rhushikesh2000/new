package demo2;

import java.util.Scanner;

public class dem01 {
	
	public static void main(String[] args) {
		
		
		
		
		
		
		int i,j,n;
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the value :");
        n=obj.nextInt();
        int m=n;// copy of n should be needed to decrement
        for (i=1;i<=n;i++)
        {
        	for (j=1;j<=n;j++)
        	{
        		if (j==i||j==m) 
        			System.out.print("*");
        			else
        		System.out.print(" ");	
        		
        	}
        	m--;
        	System.out.println();
        }
        
        obj.close();
		
	}

}
