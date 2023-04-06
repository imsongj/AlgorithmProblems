import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//5607 조합
/*
 * nCr mod p = n!(r!(n-r)!)^p-2 mod p
 */

public class Solution_5607_D3_조합_전임송 { 
	static final int MOD = 1_234_567_891;
	static long N;
	static long R;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().trim().split(" ");
			N = Integer.parseInt(input[0]);
			R = Integer.parseInt(input[1]);
			
			long a = factorial(N);
			long b = factorial(R) * factorial(N - R) % MOD;
			long answer = a * power(b, MOD - 2) % MOD;
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	public static long factorial(long n) {
		long result = 1;
		for (int i = 1; i <= n; i++) {
			result = (result * i) % MOD;
		}
		return result;
	}
	
	public static long power(long base, long exponent) {
		long result = 1;
		while (exponent > 0) {
			if ((exponent % 2) == 1) {
				result = (result * base) % MOD;
			}
			base = (base * base) % MOD;
			exponent = exponent >> 1;
		}
		return result;
	}
}