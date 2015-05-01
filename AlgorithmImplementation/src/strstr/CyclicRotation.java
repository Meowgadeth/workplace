package strstr;

import java.util.Scanner;

public class CyclicRotation {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("enter first string");
		String t1 = reader.nextLine();
		System.out.println("enter second string");
		String t2 = reader.nextLine();
		
		CyclicRotation app = new CyclicRotation();
		app.isRotation(t1,t2);
	}
	
	public void isRotation(String t1, String t2){
		if (t1.length() == t2.length()){
			String t1t1 = t1 + t1;
			if (Kmp.kmpMatch(t1t1,t2)){
				System.out.println("Cyclic rotation");
				return;
			}
		}
		System.out.println("Not cyclic rotation");
	}

}
