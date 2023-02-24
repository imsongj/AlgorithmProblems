import java.util.Arrays;

public class Practice {
	static int N = 3;
	static int M = 2;
	static int[] pick;
	public static void main(String[] args) {
		pick = new int[N];
		subset(0, 0);
		/*permutation(0, 0);
		System.out.println();
		int[] input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = i;
		}
		do {
			System.out.println(Arrays.toString(input));
		} while (nextPermutation(input));*/
	}
	
	public static void permutation(int count, int flag) {
		if (count == N) {
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
	
	public static boolean nextPermutation(int[] input) {
		int n = N - 1;
		int i = n;
		while (i > 0 && input[i - 1] >= input[i]) {
			i--;
		}
		if (i == 0) {
			return false;
		}
		int j = n;
		while (input[i - 1] >= input[j]) {
			j--;
		}
		swap(input, i - 1, j);
		int k = n;
		while (k > i) {
			swap(input, i++, k--);
		}
		return true;
	}
	
	public static void swap(int[] input, int i, int j) {
		int tmp = input[i];
		input[i] = input[j];
		input[j] = tmp;
	}
	
	public static void subset(int count, int flag) {
		if (count == N) {
			for (int i = 0; i < N; i++) {
				if ((flag & 1 << i) != 0) {
					continue;
				}
				System.out.printf("%d ", i);
			}
			System.out.println();
			return;
		}
		subset(count + 1, flag);
		subset(count + 1, flag | 1 << count);
		
	}
}
