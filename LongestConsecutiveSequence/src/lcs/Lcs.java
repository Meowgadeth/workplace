package lcs;

import java.util.Scanner;

public class Lcs {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("type in ten numbers");
		int[] input = new int[10];
		for (int i = 0; i< 10; i++){
			input[i] = reader.nextInt();
		}
		Lcs app = new Lcs();
		System.out.println(app.findLcs(input));
	}
	
	public int findLcs(int[] input){
		return 0;
	}

}
