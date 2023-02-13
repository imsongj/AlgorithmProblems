package algorithm.boj;

import java.util.Scanner;

public class Main_15649_S3_N과M1_전임송 {
	static int N, M;
	static int[] numbers;
	static boolean[] visited;
	
	public static void printNumbers() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int number : numbers) {
			stringBuilder.append(number).append(" ");
		}
		System.out.println(stringBuilder.toString());
	}
	
	public static void permutation(int count) {
		if (count == M) {
			printNumbers();
			return;
		}
		for (int number = 1; number <= N; number++) {
			if (visited[number]) {
				continue;
			}
			numbers[count] = number;
			visited[number] = true;
			permutation(count + 1);
			visited[number] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N  = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[M];
		visited = new boolean[N + 1];
		
		permutation(0);
	}
}
