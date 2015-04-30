package maxiumSubarray;

import java.util.Scanner;

public class MaxiumSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaxiumSubarray app = new MaxiumSubarray();
		System.out.println("Type in the size of the input array");
		Scanner read = new Scanner(System.in);
		int size = read.nextInt();
		if (size == 0){
			return;
		}
		int[] input = new int[size];
		System.out.println("type in"+size+" integers");
		for (int i = 0; i < size; i++){
			input[i] = read.nextInt();
		}
		int result = app.maxSubArray(input,0,size - 1);
		System.out.println("Maxium subarray is "+result);
	}
	public int maxSubArray(int[] A, int low, int high) {
		if (high <= low){
			return A[high];
		}else{
			int mid = (int)Math.ceil((high + low)/2.0);
			return multiMax(maxSubArray(A,low, mid-1),maxSubArray(A,mid,high),maxCross(A,mid,low,high));
		}
    }
	public int maxCross(int[] A, int mid, int low, int high){
		int max_left= Integer.MIN_VALUE;
		int sum = 0;
		for (int i = mid-1; i >= low; i--){
			sum = sum + A[i];
			if (sum > max_left){
				max_left = sum;
			}
		}
		int max_right = Integer.MIN_VALUE;
		sum = 0;
		for (int i = mid; i <= high; i++){
			sum = sum + A[i];
			if (sum > max_right){
				max_right = sum;
			}
		}
		return (max_left+max_right);
	}
	
	public int multiMax(int... values){
		int max = values[0];
		for (int i= 1; i < values.length; i++){
			max = Math.max(max, values[i]);
		}
		return max;
	}

}
