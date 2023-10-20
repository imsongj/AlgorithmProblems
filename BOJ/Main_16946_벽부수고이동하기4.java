import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main_16946_벽부수고이동하기4 {
    /*
    모든 0을 연결된 0들 수로 채운다.
    모든 1에서 4방향으로 더한다.

   	*/
	public static int[] dr = {0, 0, -1, 1};
	public static int[] dc = {-1, 1, 0, 0};

	public static int N;
	public static int M;
	public static int[][] board;
	public static int[][] count;
	public static int[][] group;
    public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		board = new int[N][M];
		group = new int[N][M];
		Map<Integer, Integer> count = new HashMap<>();
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < M; c++) {
				board[r][c] = line.charAt(c) - '0';
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] == 1 || group[r][c] != 0) {
					continue;
				}
				int parent = r * M + c + 1;
				int totalCount = dfs(r, c, parent);
				count.put(parent, totalCount);
			}
		}

		for (int r = 0; r < N; r++) {
			StringBuilder sb = new StringBuilder();
			for (int c = 0; c < M; c++) {
				if (board[r][c] == 0) {
					sb.append(0);
					continue;
				}
				int sum = 1;
				Set<Integer> set = new HashSet<>();
				for (int d = 0; d < 4; d++) {
					int newR = r + dr[d];
					int newC = c + dc[d];
					if (newR < 0 || newC < 0 || newR >= N || newC >= M) {
						continue;
					}
					if (board[newR][newC] != 0) {
						continue;
					}
					if (set.contains(group[newR][newC])) {
						continue;
					}
					sum += count.get(group[newR][newC]);
					set.add(group[newR][newC]);
				}
				sb.append(sum % 10);
			}
			System.out.println(sb);
		}
		// for (int i = 0; i < N; i++) {
		// 	System.out.println(Arrays.toString(count[i]));
		//
		// }
		// for (int i = 0; i < N; i++) {
		// 	System.out.println(Arrays.toString(group[i]));
		//
		// }

	}
	public static int dfs(int r, int c, int parent) {
		int count = 1;
		group[r][c] = parent;
		for (int d = 0; d < 4; d++) {
			int newR = r + dr[d];
			int newC = c + dc[d];
			if (newR < 0 || newC < 0 || newR >= N || newC >= M) {
				continue;
			}
			if (board[newR][newC] == 1 || group[newR][newC] != 0) {
				continue;
			}
			count += dfs(newR, newC, parent);
		}
		return count;
	}
}