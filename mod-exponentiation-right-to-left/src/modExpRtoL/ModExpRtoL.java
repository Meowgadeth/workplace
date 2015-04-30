package modExpRtoL;
import java.util.Scanner;

public class ModExpRtoL {
	public static void main(String[] args) {
		ModExpRtoL app = new ModExpRtoL();
		
		System.out.println("enter reverse bitwise representation of exponent");
		Scanner reader = new Scanner(System.in);
		
		String bitwiseExp = reader.nextLine();
		char[] bChar = bitwiseExp.toCharArray();
		int[] bInt = new int[bChar.length];
		for (int i = 1; i < bChar.length; i++){
			bInt[i] = Character.getNumericValue(bChar[i]);
		}
		
		System.out.println("enter base of exponention");
		int a = reader.nextInt(); 
		
		System.out.println("enter mod base n");
		int n = reader.nextInt();
		
		System.out.println(app.modExp(a,bInt,n));
	}
	
	public int modExp(int a, int[] b, int n){
		int total = 1;
		int partial = a;
		for (int i = 0; i < b.length; i++){
			if (b[i] == 1){
				total = (total * partial)%n;
			}
			partial = (partial * partial)%n;
		}
		return total;
	}
}
