import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main_17404_RGB거리2 {
    /*
    17404 RGB거리2
		3가지 색 중 한가지 색만 사용해서 나온 값들 중 그 한가지 색을 뺀 두 값을 비교한다 * 3
   	*/
	public static final int MAX = 1_000_001;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3];
        int[][] dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            cost[i][0] = Integer.parseInt(input[0]);
            cost[i][1] = Integer.parseInt(input[1]);
            cost[i][2] = Integer.parseInt(input[2]);
        }
        
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];
        int minDist = Integer.MAX_VALUE;
        int[] sum = new int[2];
        boolean isFilled = false;
        for (int lastColor = 0; lastColor < 3; lastColor++) {
        	for (int r = 1; r < N; r++) {
        		for (int thisColor = 0; thisColor < 3; thisColor++) {
        			sum[0] = 1000001;
        			sum[1] = 1000001;
        			isFilled = false;
        			for (int prevColor = 0; prevColor < 3; prevColor++) {
        				if (thisColor == prevColor) {
        					continue;
        				}
        				if (r == 1) {
        					if (prevColor != lastColor) {
        						continue;
        					}
        				}
        				if (isFilled) {
        					sum[1] = cost[r][thisColor] + dp[r - 1][prevColor];
        					continue;
        				}
        				sum[0] = cost[r][thisColor] + dp[r - 1][prevColor];
        				isFilled = true;
        			}
        			dp[r][thisColor] = Math.min(sum[0], sum[1]);
        		}
        	}
        	for (int i = 0; i < 3; i++) {
        		if (i == lastColor) {
        			continue;
        		}
            	minDist = Math.min(minDist, dp[N - 1][i]);
            }
        }
        System.out.println(minDist);
    }
}