package algorithm.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution_2001_D2_파리퇴치_전임송 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			
			int board[][] = new int[n + 1][n + 1];
			
			int maxSum = 0;
			for (int r = 1; r <= n; r++) {
				input = br.readLine().split(" ");
				for (int c = 1; c <= n; c++) {
					board[r][c] = Integer.parseInt(input[c - 1]) 
							+ board[r][c - 1] + board[r - 1][c] - board[r - 1][c - 1]; //누적합 저장
				}
			}
			
			
			for (int r = m; r <= n; r++) {
				for (int c = m; c <= n; c++) {
					int sum = board[r][c] - board[r - m][c] -board[r][c - m] + board[r - m][c - m]; //누적합 계산
					maxSum = Math.max(maxSum, sum);
				}
			}
			bw.append(String.format("#%d %d%n", t, maxSum));
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
