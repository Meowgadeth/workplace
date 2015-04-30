package ccString;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		sol.countAndSay(1);
	}
	
	public String countAndSay(int n) {
        String ans = Integer.toString(1);
        for (int i = 1; i < 5; i++){
            ans = countOnce(ans);
        }
        System.out.println("==="+ans+"===");
        return ans;
    }
    public String countOnce(String input){
    	System.out.println("input is " + input);
        String result = "";
        int count = 1;
        for(int i = 0; i < input.length(); i++){
            if (i + 1 < input.length() && input.charAt(i+1) == input.charAt(i)){
                count++;
            }
            else{
                result += Integer.toString(count) + input.charAt(i);
                count = 1;
            }
            System.out.println(result);
        }
        System.out.println("+++++++" + result);
        return result;        
    }

}
