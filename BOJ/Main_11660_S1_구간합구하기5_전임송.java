package algorithm.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_11660_S1_구간합구하기5_전임송 {
	static int[][] sums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		
		sums = new int[n + 1][n + 1];
		for (int r = 1; r <= n; r++) {
			input = br.readLine().split(" ");
			for (int c = 1; c <= n; c++) {
				sums[r][c] = Integer.parseInt(input[c - 1]);
			}
		}
		
		for (int r = 1; r <=n; r++) {
			for (int c = 1; c <= n; c++) {
				sums[r][c] += sums[r][c - 1] + sums[r - 1][c] - sums[r - 1][c - 1];
			}
		}
		
		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int startR = Integer.parseInt(input[0]);
			int startC = Integer.parseInt(input[1]);
			int endR = Integer.parseInt(input[2]);
			int endC = Integer.parseInt(input[3]);
			int sum = sums[endR][endC] - sums[endR][startC - 1] - sums[startR - 1][endC] 
					+ sums[startR - 1][startC - 1];
			bw.append(Integer.toString(sum)).append('\n');
		}
		bw.flush();
		br.close();
		bw.close();
	}
}