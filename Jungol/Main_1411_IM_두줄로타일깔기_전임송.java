import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1411 두줄로타일깔기
/*
 * f(0) = 1;
 * f(1) = 1;
 * f(2) = 3 = f(2 - 1) + 2*f(2-2) 
 */
public class Main_1411_IM_두줄로타일깔기_전임송 {
	static final int D = 20100529; 
	static int N;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % D; 
		}
		System.out.println(dp[N]);
	}
}
