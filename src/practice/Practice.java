package practice;
import java.util.Arrays;

public class Practice {
	static int N;
	static int[] pick;
	public static void main(String[] args) {
		/*int[] input = new int[] {1,2,3,4,5};
		do {
			System.out.println(Arrays.toString(input));
		} while (nextPermutation(input));*/
		pick = new int[N];
	}
	public static void permutation(int count, int flag) {
		if (count == N) {
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) {
				continue;
			}
			pick[count] = 1;
			permutation(count + 1, flag | 1 << i);
		}
		
	}
	
	public static void combination(int count, int start) {
		if (count == N) {
			return;
		}
		for (int i = start; i < N; i++) {
			pick[count] = i;
			combination(count + 1, i + 1);
		}
	}
	public static void subset(int count, int flag) {
		if (count == N) {
			for (int i = 0; i < N; i++) {
				if ((flag & 1 << i) != 0) {
					continue;
				}
				System.out.println(i);
			}
			return;
		}
		subset(count + 1, flag | 1 << count);
		subset(count + 1, flag);
	}
	private static boolean nextPermutation(int[] input) {
		int n = input.length - 1;
		int i = n;
		while (i > 0 && input[i] <= input[i - 1]) {
			i--;
		}
		if (i == 0) {
			return false;
		}
		int j = n;
		while (input[j] <= input[i - 1]) {
			j--;
		}
		swap(input, i - 1, j);
		int k = n;
		while (k > i) {
			swap(input, k--, i++);
		}
		return true;
	}
	
	private static void swap(int[] input, int i, int j) {
		int tmp = input[i];
		input[i] = input[j];
		input[j] = tmp;
		
	}
}
