package algorithm.update;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1861_D4_정사각형방_전임송 { //1861
	static int N;
	static int[][] board;
	static int maxCount;
	static int maxStart;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			String[] input;
			for (int r = 0; r < N; r++) {
				input = br.readLine().split(" ");
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(input[c]);
				}
			}
			maxCount = 0;
			maxStart = N * N + 1;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					dfs(board[r][c], r, c, 1);
				}
			}
			sb.append(String.format("#%d %d %d", t, maxStart, maxCount));
			System.out.println(sb.toString());
		}
		br.close();
	}
	
	private static void dfs(int start, int currentR, int currentC, int count) {
		for (int d = 0; d < 4; d++) {
			int newR = currentR + dr[d];
			int newC = currentC + dc[d];
			if (newR < 0 || newR >= N || newC < 0 || newC >= N) {
				continue;
			}
			if (board[newR][newC] != board[currentR][currentC] + 1) {
				if (count > maxCount) {
					maxCount = count;
					maxStart = start;
				}
				if (count == maxCount && start < maxStart) {
					maxStart = start;
				}
				continue;
			}
			dfs(start, newR, newC, count + 1);
		}
	}
}
