import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//3307 최장증가부분수열

public class Solution_3307_D3_최장증가부분수열_전임송 { 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= testCase; t++) {
			int N = Integer.parseInt(br.readLine());
				 	int window = N / 4;
			int k = 0;
			int[] numbers = new int[N];
			int[] dp = new int[N];
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(input[i]);
			}
			dp[k++] = numbers[0];
			for (int i = 1; i < N; i++) {
				int placeIndex = Arrays.binarySearch(dp, 0, k, numbers[i]);
				//System.out.println(placeIndex);
				placeIndex = Math.abs(placeIndex) - 1;
				dp[placeIndex] = numbers[i];
				if (placeIndex == k) {
					k++;
				}
			}
			System.out.printf("#%d %d%n", t, k);
		}
		br.close();
	}
}