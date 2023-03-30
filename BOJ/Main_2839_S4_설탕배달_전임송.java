import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

//2839 설탕배달
/*
 * 
 */

public class Main_2839_S4_설탕배달_전임송 {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[5001];
		Arrays.fill(dp, 10_000);
		dp[3] = 1;
		dp[5] = 1;
		for (int i = 6; i <= N; i++) {
			dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
		}
		if (dp[N] >= 10_000) {
			System.out.println(-1);
			return;
		}
		System.out.println(dp[N]); 
	}
}
