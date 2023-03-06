package practice;
import java.util.Arrays;

public class Practice {
	static int N;
	static int M;
	static int[] pick;
	public static void main(String[] args) {
		N = 5;
		M = 4;
		pick = new int[M];
		//permutation(0, 0);
		//combination(0, 0);
		//subset();
		int[] input = {1,2,4,6,7};
		Arrays.sort(input);
		do {
			System.out.println(Arrays.toString(input));
		} while (nextPermutation(input));
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

	public static void permutation(int count, int flag) {
		if (count == M) {
			System.out.println(Arrays.toString(pick));
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) {
				continue;
			}
			pick[count] = i;
			permutation(count + 1, flag | 1 << i);
		}
	}
	
	public static void combination(int count, int start) {
		if (count == M) {
			System.out.println(Arrays.toString(pick));
			return;
		}
		for (int i = start; i < N; i++) {
			pick[count] = i;
			combination(count + 1, i + 1);
		}
	}
	
	public static void subset() {
		for (int s = 0; s < 1 << N; s++) {
			for (int i = 0; i < N; i++) {
				if ((s & 1 << i) == 0) {
					continue;
				}
				System.out.printf("%d ", i);
			}
			System.out.println();
		}
	}
	
	
}
