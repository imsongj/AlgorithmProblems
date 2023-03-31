package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LIS2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] c = new int[N];
		String[] line = br.readLine().split(" ");
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(line[i]);
		}
		int k = 0;
		c[k++] = arr[0];
		for (int i = 1; i < N; i++) {
			int tmp = Arrays.binarySearch(c, 0, k, arr[i]);
			tmp = Math.abs(tmp) - 1;
			c[tmp] = arr[i];
			if (k == tmp) {
				k++;
			
			}
		}
		System.out.println(k);
	}
}
