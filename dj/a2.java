package dj;

import java.util.Scanner;

public class a2 {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner (System.in);
		String a=sc.next();
		System.out.println(m1(a));
		
		}
	

	public static boolean m1(String a) {
		int b=0;
		for (int i = 0; i < a.length() &&i+1<a.length(); i++) {
			if (a.charAt(i)=='b' &&a.charAt(i+1)=='a') {
				
			b=1;
				
			}
			else {
			b=0;
			}
			
			
	
		}
		
		if (b==1) {
			return false;
			
		}
		else {
			return true;
		}
	}}
