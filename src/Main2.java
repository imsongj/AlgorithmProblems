import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//13913 숨바꼭질4
/*
 * f(n) = 0;
 * f(n/3) = f(n) + 1
 * f(n/2) = f(n) + 1
 * f(n - 1) = f(n) + 1
 */
public class Main2 {
	static final int START = 0;
	static final int END =  100_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int K = Integer.parseInt(line[1]);
		int[] dp = new int[END + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[N] = 0;
		
		for (int i = N; i <= END; i++) {
			if (i * 2 <= END) {
				dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
			}
			if (i + 1 <= END) {
				dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
			}
		}
		for (int i = N; i >= START; i--) {
			if (i - 1 >= START) {
				dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
			}
		}
		System.out.println(dp[K]);
	}
}
