import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//1278 배낭채우기2
/*
 * 
 */
public class Main_1278_IM_배낭채우기2_전임송 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int W = Integer.parseInt(line[1]);
		int[] jWeights = new int[N + 1];
		int[] jPrices = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			line = br.readLine().split(" ");
			jWeights[i] = Integer.parseInt(line[0]);
			jPrices[i] = Integer.parseInt(line[1]);
		}
		int[][] dp = new int[N + 1][W + 1];
		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= W; w++) {
				if (jWeights[i] > w) {
					dp[i][w] = dp[i - 1][w];
					continue;
				}
				dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - jWeights[i]] + jPrices[i]);
			}
		}
		System.out.println(dp[N][W]);
	}
}
