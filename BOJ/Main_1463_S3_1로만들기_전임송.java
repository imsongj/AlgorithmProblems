import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//1463 1로만들기
/*
 * f(n) = 0;
 * f(n/3) = f(n) + 1
 * f(n/2) = f(n) + 1
 * f(n - 1) = f(n) + 1
 */
public class Main_1463_S3_1로만들기_전임송 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[N] = 0;
		
		for (int i = N; i > 1; i--) {
			if (i % 3 == 0) {
				dp[i / 3] = Math.min(dp[i / 3], dp[i] + 1);
			}
			if (i % 2 == 0) {
				dp[i / 2] = Math.min(dp[i / 2], dp[i] + 1);
			}
			dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
		}
		System.out.println(dp[1]);
	}
}
