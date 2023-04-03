package practice;

import java.util.Arrays;

public class SubsetPractice {
	static int[] parent;
	public static void main(String[] args) {
		parent = new int[6];
	}
	private void makeSet(int n) {
		parent[n] = n;
	}
	private int findSet(int n) {
		if (parent[n] == n) {
			return n;
		}
		return parent[n] = findSet(parent[n]);
	}
	private void union (int n, int m) {
		parent[m] = parent[n];
	}
	private static boolean nextPermutation(int[] input) {
		int n = input.length - 1;
		int i = n;
		while (i > 0 && input[i] <= input[i - 1]) {
			i--;
		}
		if (i ==0 ) {
			return false;
		}
		int j = n; 
		while (input[j] <= input[i - 1]) {
			j--;
		}
		swap(input, i-1, j);
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
