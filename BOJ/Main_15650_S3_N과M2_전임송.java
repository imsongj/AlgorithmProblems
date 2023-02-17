package algorithm.boj;

import java.util.Scanner;

public class Main_15650_S3_N과M2_전임송 {
	static final int BASE = 1;
	
	static int N;
	static int M;
	static int[] numbers;
	static StringBuilder stringBuilder;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[M];
		stringBuilder = new StringBuilder();
		
		combination(0, BASE);
		
		System.out.println(stringBuilder.toString());
	}

	private static void combination(int count, int start) {
		if (count == M) {
			stringBuilder.append(toString(numbers));
			return;
		}
		for (int i = start; i <= N; i++) {
			numbers[count] = i;
			combination(count + 1, i + 1);
		}
	}
	
	private static String toString(int[] numbers) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int number : numbers) {
			stringBuilder.append(number).append(' ');
		}
		return stringBuilder.append('\n')
				.toString();
	}
}