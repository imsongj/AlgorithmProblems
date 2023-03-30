import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//1077 배낭채우기1
/*
 * 
 */
public class Main_1077_IM_배낭채우기1_전임송 {
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
		int[] dp = new int[W + 1];
		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= W; w++) {
				if (jWeights[i] > w) {
					continue;
				}
				dp[w] = Math.max(dp[w], dp[w - jWeights[i]] + jPrices[i]);
			}
		}
		System.out.println(dp[W]);
	}
}
