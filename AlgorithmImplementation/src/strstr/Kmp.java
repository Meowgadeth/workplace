package strstr;

public class Kmp {	
	public static void main(String[] args) {
		Kmp app = new Kmp();
		String p = "ababaca";  //pattern initialize
		String t = "bacbababaababaca";   //text initialize
		
		app.kmpMatch(t, p);		
	}
	
	public static int[] computePrefix(String p){
		int m = p.length();
		int[]  pi = new int[m];
		pi[0] = 0;
		int k = 0;
		for (int q = 1; q < m; q++){
			while (k > 0 && (p.charAt(k) != p.charAt(q))){
				k = pi[k-1];
			}
			if (p.charAt(k) == p.charAt(q)){
				k++;
			}
			pi[q] = k;
		}
		for (int i = 0; i < pi.length; i++){
			System.out.print(pi[i]+" ");
		}
		System.out.println();
		return pi;
	}
	public static boolean kmpMatch(String t, String p){
		int n = t.length();
		int m = p.length();
		int[] pi = computePrefix(p);
		int q = 0;
		for (int i = 0; i < n; i++){
			while (q > 0 && (p.charAt(q) != t.charAt(i))){
				q = pi[q - 1];
			}
			if (p.charAt(q) == t.charAt(i)){
				q = q + 1;
			}
			if (q == m){
				//System.out.println("pattern occurs with shift "+ (i - m));
				return true;
			}
		}
		return false;
	}
}
