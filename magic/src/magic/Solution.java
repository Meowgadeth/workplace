package magic;

import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
        String columnAndRows = in.readLine();
        int m = Integer.parseInt(columnAndRows.split(" ")[0]);
        int n = Integer.parseInt(columnAndRows.split(" ")[1]);
        
        String[] input = new String[m];
        for (int i = 0; i < m; i++){
        	input[i] = in.readLine();
        }      
        Solution.findMaxStraight(input);
            
    }
    public static void findMaxStraight(String[] input){
    	HashMap<String,Integer> map = new HashMap<>();
    	normalize(input);
    	int max = 0;
    	for (int i = 0; i < input.length; i++){
    		if (!map.containsKey(input[i])){
    			map.put(input[i],1);
    		}
    		else{
    			map.put(input[i],map.get(input[i])+1);
    			max = map.get(input[i]) > max? map.get(input[i]):max;
    		}
    	}
    	System.out.println(max);
    }
    
    public static void normalize(String[] input){
    	for (int i = 0; i < input.length; i++){
    		if(input[i].charAt(0) == 'T') flipRow(input,i);
    	}
    	
    }
    
    public static void flipRow(String[] input, int row){
    	char[] chars = input[row].toCharArray();
    	for (int i = 0; i < chars.length; i++){
    		chars[i] = chars[i] == 'T'? 'P':'T';
    	}
    	input[row] = new String(chars);
    }
}
