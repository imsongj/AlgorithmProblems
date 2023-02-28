package practice;
import java.util.Arrays;

public class Practice {
	public static void main(String[] args) {
		/*int[] input = new int[] {1,2,3,4,5};
		do {
			System.out.println(Arrays.toString(input));
		} while (nextPermutation(input));*/
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
