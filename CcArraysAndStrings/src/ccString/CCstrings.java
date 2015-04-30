package ccString;

import java.util.Arrays;

public class CCstrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CCstrings app = new CCstrings();
		int[][] tests1 = new int[][]{{1,2,3,4},{2,0,0,3},{3,0,0,2},{4,3,2,1}};
		int[][] tests2 = new int[][]{{1,0,1},{2,2,2},{3,3,3}};
		int[][] tests3 = new int[][]{{1,1},{2,0}};
		int[][] tests4 = new int[][]{{1}};
		int[][] tests6 = new int[][]{{1,2,3,4,0,5,6},{0,0,0,0,0,0,0},{0,0,0,0,1,2,3},
				{1,2,3,4,6,6,0}};
		
		printMatrix(tests1);
		CCstrings.setZero(tests1);
		System.out.println();
		printMatrix(tests1);
		System.out.println("========");
		printMatrix(tests2);
		CCstrings.setZero(tests2);
		System.out.println();
		printMatrix(tests2);
		System.out.println("========");  
		printMatrix(tests3);
		CCstrings.setZero(tests3);
		System.out.println();
		printMatrix(tests3);
		System.out.println("========");
		printMatrix(tests4);
		CCstrings.setZero(tests4);
		System.out.println();
		printMatrix(tests4);
		System.out.println("========");
		printMatrix(tests6);
		CCstrings.setZero(tests6);
		System.out.println();
		printMatrix(tests6);
	}
	
	public static void printMatrix(int[][] input){
		for (int i = 0; i < input.length; i++){
			for (int j = 0; j < input[0].length; j++){
				System.out.print(input[i][j]);
			}
			System.out.println();
		}
	}
	
	public boolean hasDup(String input){
		for (int i = 0; i < input.length() - 1; i++){
			for (int j = i +1; j < input.length(); j++){
				if(input.charAt(i) == input.charAt(j)){
					return true;
				}
			}
		}
		return false;
	}
	
	//answer 
//	public static boolean isUniqueChars2(String str) {
//		boolean[] char_set = new boolean[256];
//		for (int i = 0; i < str.length(); i++) {
//		    int val = str.charAt(i);
//		    if (char_set[val]) return false;
//		    char_set[val] = true;
//		}
//		return true;
//	}
//	
//	public static boolean isUniqueChars(String str) {
//		int checker = 0;
//		for (int i = 0; i < str.length(); ++i) {
//		    int val = str.charAt(i) - 'a';
//		    if ((checker & (1 << val)) > 0) return false;
//		    checker |= (1 << val);
//		}
//		return true;
//	}
	
	public String cStringReverse(String input){
		int pos = 0; //current position in chars array
		char[] chars = new char[input.length()];
		for (int i = input.length() - 2; i >= 0; i--){
			chars[pos] = input.charAt(i);
			pos++;
		}
		chars[pos] = '#';
		return new String(chars);
	}
	
	//answer is using c++
	
	public String removeDup(String input){
		char[] chars = input.toCharArray();
		int start = 0;
		int end = input.length() - 1;
		while (start < end){
			int i = start + 1;
			while (i <= end){
				if (chars[start] == chars[i]){
				    char temp = chars[i];
				    chars[i] = chars[end];
				    chars[end] = temp;
				    end--;
				}
				else{
					i++;
				}
			}
			start++;
		}
		char[] B = new char[end+1];
		for (int i = 0; i < B.length; i++){
			B[i] = chars[i];
		}
		return new String(B);
	}
	
	//answers: removing in place
//	public static void removeDuplicates(char[] str) {
//		if (str == null) return;
//		int len = str.length;
//		if (len < 2) return;
//		int tail = 1;
//		for (int i = 1; i < len; ++i) {
//		    int j;
//		    for (j = 0; j < tail; ++j) {
//		        if (str[i] == str[j]) break;
//		    }
//		    if (j == tail) {
//		    	str[tail] = str[i];
//		    	++tail;
//		    }
//		}
//		str[tail] = 0;
//	}
	
	//answer: use array size of 256 as lookup
//	public static void removeDuplicatesEff(char[] str) {
//		2 if (str == null) return;
//		3 int len = str.length;
//		4 if (len < 2) return;
//		5 boolean[] hit = new boolean[256];
//		6 for (int i = 0; i < 256; ++i) {
//		7  hit[i] = false;
//		8 }
//		9 hit[str[0]] = true;
//		10  int tail = 1;
//		11 for (int i = 1; i < len; ++i) {
//		12  if (!hit[str[i]]) {
//		13  str[tail] = str[i];
//		14  ++tail;
//		15  hit[str[i]] = true;
//		16  }
//		17 }
//		18 str[tail] = 0;
//		19 }
	
	public boolean isAnagram(String str1, String str2){
		char[] chars1 = str1.toLowerCase().replaceAll("\\s+", "").toCharArray();
		char[] chars2 = str2.toLowerCase().replaceAll("\\s+", "").toCharArray();
		if (chars1.length == chars2.length){
			Arrays.sort(chars1);
			Arrays.sort(chars2);
			for (int i = 0; i < chars1.length; i++){
				if (chars1[i] != chars2[i]){
					return false;
				}
			}
			return true;
		}
		return false;		
	}
	
	//similar to mine, check string equal instead of array equal would be easier
	
	//check if to string has same count for each char, using char[256]
	
	public static void replaceWith(String input){
		//lue, no access to internet
		//doesn't understand question
	}
	
	public static void rotateImage(int[][] input){
		int n = input.length;
		System.out.println("input size is "+ n);
		for (int i = 0; i < Math.ceil(n/2.0); i++){
			for (int j = i; j < n-1; j++){
				int temp = input[i][j];
				input[i][j] = input[n-j-1][i];
				input[n-j-1][i] = input[n-i-1][n-j-1];
				input[n-i-1][n-j-1] = input[j][n-i-1];
				input[j][n-i-1] = temp;
			}
		}
	}
	
	public static void setZero(int[][] input){
		int[] rowCheck = new int[input.length];
		int[] colCheck = new int[input[0].length];
		for (int i = 0; i < input.length; i++){
			for (int j = 0; j < input[0].length; j++){
				if (input[i][j] == 0){
					rowCheck[i] = 1;
					colCheck[j] = 1;
				}
			}
		}
		for (int i = 0; i < rowCheck.length; i++){
			if (rowCheck[i] == 1){
				zeroRow(input,i);
			}
		}
		for (int i = 0; i < colCheck.length; i++){
			if (colCheck[i] == 1){
				zeroColumn(input,i);
			}
		}
	}
	
	public static void zeroRow(int[][] input, int rowNum){
		for (int i = 0; i < input[rowNum].length; i++){
			input[rowNum][i] = 0;
		}
	}
	
	public static void zeroColumn(int[][] input, int colNum){
		for (int j = 0; j < input.length; j++){
			input[j][colNum] = 0;
		}
	}
	
	//use concatenation and isString to check if a string is the rotation of the other
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
