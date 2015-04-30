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
		return new int[] {1,2,3};
	}

	public void kmpMatch(String t, String p){
		//to do
	}
}
