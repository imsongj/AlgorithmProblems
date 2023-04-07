import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
 * 2042 구간합구하기
 * 
 */

public class Main_2042_G1_구간합구하기_전임송 {		
	private static final int UPDATE = 1;
	private static final int SUM = 1;
	static int N;
	static long[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		long[] numbers = new long[N];
		tree = new long[N + 1];	
		for (int i = 0; i < N; i++) {
			numbers[i] = Long.parseLong(br.readLine());
		}
		//System.out.println(Arrays.toString(numbers));
		for (int i = 1; i <= N; i++) {
			add(i, numbers[i - 1]);
		}
		//System.out.println(Arrays.toString(tree));
		for (int i = 0; i < M + K; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			long c = Long.parseLong(input[2]);
			if (a == UPDATE) {
				update(b, numbers[b - 1], c);
				numbers[b - 1] = c;
				continue;
			}
			sb.append(rangeSum((int)b,(int)c)).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void add(int i, long numbers) {
		while (i < N + 1) {
			tree[i] += numbers;
			i += (i & -i); //k 값 (제일 마지막 1)
		}
	}
	
	public static void update(int b, long numbers, long c) {
		while (b < N + 1) {
			tree[b] -= numbers;
			tree[b] += c;
			b += (b & -b); //k 값 (제일 마지막 1)
		}
		System.out.println(Arrays.toString(tree));
	}
	
	public static long sum(int c) {
		long ans = 0;
		while (c > 0) {
			ans += tree[c];
			c -= (c & -c);
		}
		return ans;
	}
	public static long rangeSum(int b, int c) {
		return sum(c) - sum(b - 1);
	}
}
