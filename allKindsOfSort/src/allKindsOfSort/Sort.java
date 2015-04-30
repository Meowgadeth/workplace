package allKindsOfSort;
import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sort app = new Sort();
		int[] input1 = new int[]{4,5,5,6,7,8,2,5,3,4};
		int[] input2 = new int[]{-1,-1,0,2,5,-3,4,9,10};
		int[] input3 = new int[]{};
		int[] input4 = new int[]{1,2,2,2};
		int[] input5 = new int[]{6};
		System.out.println(Arrays.toString(app.removeDup(input1)));
		System.out.println(Arrays.toString(app.removeDup(input2)));
		System.out.println(Arrays.toString(app.removeDup(input3)));
		System.out.println(Arrays.toString(app.removeDup(input4)));
		System.out.println(Arrays.toString(app.removeDup(input5)));
		//app.printprint(5);
		
	}
	
	public void insertionSort(int[] A){
		for(int i = 0; i < A.length; i++){
			int j = i -1;
			int temp;
			while (j >= 0 && A[j] > A[j + 1]){
				temp = A[j];
				A[j] = A[j + 1];
				A[j + 1] = temp;
				j--;
			}
		}
	}
	
	public void selectionSort(int[] A){
		for(int i = 0; i < A.length; i++){
			int minIndex = i;
			for (int j = i; j < A.length; j++){
				if (A[j] < A[minIndex]){
					minIndex = j;
				}
			}
			int temp;
			temp = A[i];
			A[i] = A[minIndex];
			A[minIndex] = temp;
		}
		
	}
	//amazon oa practice 
	public int[] removeDup(int[] A){
		if (A.length == 0){
			return A;
		}
		int end = A.length-1;
		int start = 0;
		int temp = 0;
		
		while (start < end){
			int i = start + 1;
			while (i <= end){
				if (A[start] == A[i]){
					temp = A[i];
					A[i] = A[end];
					A[end] = temp;
					end--;
				}
				else{
					i++;
				}
			}
			start++;
		}
		int[] B = new int[end+1];
		for (int i = 0; i < B.length; i++){
			B[i] = A[i];
		}
		return B;
	}
	
	public void printprint(int reps){
		int length = 1;
		for (int i = 0; i < reps; i++){
			char[] newline = new char[length];
			for (int j = 0; j < length; j++){
				newline[j] = 'a';
			}
			String print = new String(newline);
			System.out.println(print);
			length*=2;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
