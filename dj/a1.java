package dj;

import java.util.Arrays;
import java.util.Scanner;

public class a1 {
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		int a=sc.nextInt();
		System.out.println(m1(a));
		
	
	}
		
	
	public static StringBuffer m1(int a ) {
		String[] x=new String[a];
		for (int i = 0; i<a; i++) {
			if(i%2!=0) {
				x[i]="-";
			}
			else
				x[i]="+";
			
		}
		StringBuffer sb = new StringBuffer();
	      for(int i = 0; i < x.length; i++) {
	         sb.append(x[i]);
	      }
	    
	      return sb;
		
	
	
	}
	}
	
