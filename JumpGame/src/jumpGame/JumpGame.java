package jumpGame;

public class JumpGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JumpGame app = new JumpGame();
		int A[] = new int[] {5,9,3,2,1,0,2,3,3,1,0,0};
		if(app.canJump(A)){
			System.out.println("of course it can jump bitch\n");
		}else{
			System.out.println("of course it can't jump bitch");
		}
	}
	
	public boolean canJump(int[] A) {
        int curr = 0;
        while (curr < A.length-1){
            if (curr + A[curr] >= A.length-1){
                return true;
            }
            int maxJump = curr;
            int next = curr;
            for (int i= curr; i <= (A[curr] + curr); i++){
                if (i + A[i]>maxJump){
                	maxJump = i + A[i];
                	next = i;
                }
            }
            if (curr == maxJump){
                return false;
            }
            else{
                curr = next;
            }
        }
        return true;
    }
}
