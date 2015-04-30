package strstr;

public class Kmp {

	public static void main(String[] args) {
		Kmp app = new Kmp();
		String p = "ababaca";  //pattern initialize
		int[] pi = app.computePrefix(p);  //prefix function initialize
		String t = "bacbababaababaca";   //text initialize
		
		app.kmpMatch(t, p);		
	}
	
	public int[] computePrefix(String p){
		int m = p.length();
		int[]  pi = new int[m];
		pi[1] = 0;
		int k = 0;
		for (int q = 2; q <= m; q++){
			while (k > 0 && (p.charAt(k+1) != p.charAt(q))){
				k = pi[k];
			}
			
			//to do
		}
	}

	public void kmpMatch(String t, String p){
		//to do
	}
}
