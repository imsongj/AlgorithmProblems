package practice;

import java.util.Arrays;

public class FibonacciTest1 {
	private static long fibo1(int n) {
		if (n <= 1) {
			return n;
		}
		return fibo1(n - 1) + fibo1(n - 2);
	}
	
	static long[] memo;
	private static long fibo2(int n) {
		if (memo[n] >= 0) {
			return memo[n];
		}
		return fibo2(n - 1) + fibo2(n - 2);
	}
	
	public static void main(String[] args) {
		int n = 10;
		memo = new long[n + 1];
		Arrays.fill(memo, -1);
		memo[0] = 0;
		memo[1] = 1;
	}

}

