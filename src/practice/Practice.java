package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;



public class Practice {
	public static void main(String[] args) {
		int[] input = {1, 2,3,4,5};
		Arrays.sort(input);
		do {
			System.out.println(Arrays.toString(input));
		} while (nextPermutation(input));
	}

	private static boolean nextPermutation(int[] input) {
		int n = input.length - 1;
		int i = n;
		while (i > 0 && input[i] <= input[i-1]) {
			i--;
		}
		if (i == 0) {
			return false;
		}
		int j = n;
		while (input[j] <= input[i-1]) {
			j--;
		}
		swap(input, i- 1, j);
		int k = n;
		while (k > i) {
			swap(input, i++, k--);
		}
		
		return true;
	}

	private static void swap(int[] input, int i, int j) {
		// TODO Auto-generated method stub
		
	}
}
