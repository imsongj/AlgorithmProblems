import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main_16724_피리부는사나이 {
	public static int[] dc = {0, 0, -1, 1};
	public static int[] dr = {-1, 1, 0, 0};
	public static int N;
	public static int M;
	public static char[][] board;
	public static int[][] count;
	public static int[][] group;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		board = new char[N][M];
		group = new int[N][M];
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < M; c++) {
				board[r][c] = line.charAt(c);
			}
		}
		int count = 0;
		Set<Integer> set = new HashSet<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (group[r][c] != 0) {
					continue;
				}
				int parent = r * M + c + 1;
				int result = dfs(r, c, parent);
				if (set.contains(result)) {
					continue;
				}
				set.add(result);
				count++;
			}
		}
		//print group array
		// for (int r = 0; r < N; r++) {
		// 	System.out.println(Arrays.toString(group[r]));
		// }
		System.out.println(count);
	}

	public static int dfs(int r, int c, int parent) {
		group[r][c] = parent;
		int d = getDirection(board[r][c]);
		int newR = r + dr[d];
		int newC = c + dc[d];
		// if (newR < 0 || newC < 0 || newR >= N || newC >= M) {
		// 	return parent;
		// }
		if (group[newR][newC] != 0) {
			if (group[newR][newC] != parent) {
				group[r][c] = group[newR][newC];
				return group[newR][newC];
			}
			return parent;
		}
		group[r][c] = dfs(newR, newC, parent);
		return group[r][c];
	}

	static int getDirection(char letter) {
		if (letter == 'U') {
			return 0;
		}
		if (letter == 'D') {
			return 1;
		}
		if (letter == 'L') {
			return 2;
		}
		if (letter == 'R') {
			return 3;
		}
		return 0;
	}
}