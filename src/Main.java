import java.io.*;
import java.util.*;

public class Main {
	static int[] numbers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		int[] dp = new int[N];
		int[] fromStart = new int[N];
		int[] fromEnd = new int[N];

		String[] input = br.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(input[i]);
			fromStart[i] = 1;
			fromEnd[i] = 1;
		}

		dp[0] = numbers[0];
		int count = 0;
		for (int i = 1; i < N; i++) {
			if (dp[count] < numbers[i]) {
				dp[count + 1] = numbers[i];
				count++;
			} else {
				int index = binarySearch(0, count, numbers[i]);
				dp[index] = numbers[i];
			}
			fromStart[i] = count;
		}

		dp = new int[N];
		dp[0] = numbers[N - 1];
		count = 0;
		for (int i = N - 2; i >= 0; i--) {
			if (dp[count] < numbers[i]) {
				dp[count + 1] = numbers[i];
				count++;
			} else {
				int index = binarySearch(0, count, numbers[i]);
				dp[index] = numbers[i];
			}
			fromEnd[N - i - 1] = count;
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, fromStart[i] + fromEnd[i]);
		}
		System.out.println(Arrays.toString(fromStart));
		System.out.println(Arrays.toString(fromEnd));
		System.out.println(max - 1);
	}

	public static int binarySearch(int left, int right, int target) {
		int mid;
		while (left < right) {
			mid = (left + right) / 2;
			if (numbers[mid] < target) {
				left = mid + 1;
				continue;
			}
			right = mid;
		}
		return right;
	}
}
