package dj;

import java.util.Scanner;

public class ganesh {
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		System.out.println("enter your proprty");
		double prperty=sc.nextDouble();
		System.out.println("enter your surname");
		String surname=sc.next();
		System.out.println("enter youe ege");x
		int age=sc.nextInt();
		if (age>=21 && prperty>=10000000 || surname.equals("ambani")) {
			System.out.println("cahl zavayala");
			
		}
		else {
			System.out.println("zattya paise kamav aadhi");
		}
	}

}
