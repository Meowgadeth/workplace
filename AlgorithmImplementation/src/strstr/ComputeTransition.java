package strstr;

public class ComputeTransition {

	public static void main(String[] args) {
		ComputeTransition app = new ComputeTransition();
		String input = "ababbabbababbababbabb";
		char[] alph = new char[]{'a','b'};
		app.run(input, alph);
	}
	public void run(String p, char[] A){
		int m = p.length();
		int n = A.length;
		int[][] result = new int[n][m+1];
		for (int i = 0; i <= m; i++){
			for (int j = 0; j < A.length; j++){
				int k = Math.min(m, i+1);
				char c = A[j];
				while (!suffixOf(p.substring(0, k), p.substring(0, i)+c)){
					k = k - 1;
				}
				result[j][i] = k;
			}
		}
		printResult(result,n);
	}
	public boolean suffixOf(String suffix, String src){
		int match = suffix.length();
		return suffix.equals(src.substring(src.length()-match));
	}
	public void printResult(int[][] result, int n){
		for (int i = 0; i < result[0].length; i++){
			System.out.print(i+ " : ");
			for (int j = 0; j < n; j++){
				System.out.print(result[j][i]+ " ");
			}
			System.out.println();
		}
	}

}
