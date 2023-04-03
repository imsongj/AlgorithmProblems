import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
 * 2636 치즈
 * 
 */
public class Main2 {
	static final int CHEESE = 1;
	static final int HOLE = 0;
	static int[][] board;
	static boolean[][] melted;
	static int N;
	static int M;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		board = new int[N][M];
		melted = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			line = br.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(line[c]);
			}
		}
		int air = 2;
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		int count = 0;
		int prevCount = 0;
		
		
		while (true) {
			count++;
			int cheeseCount = 0;
			
			queue.add(new int[] {0, 0});
			board[0][0] = air;
			while (!queue.isEmpty()) {
				int[] current = queue.poll();
				for (int d = 0; d < 4; d++) {
					int newR = current[0] + dr[d];
					int newC = current[1] + dc[d];
					if (newR < 0 || newR >= N || newC < 0 || newC >= M) {
						continue;
					}
					if (board[newR][newC] == air) {
						continue;
					}
					if (board[newR][newC] == CHEESE) {
						continue;
					}
					queue.add(new int[] {newR, newC});
					board[newR][newC] = air;
				}
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (melt(r, c, air)) {
						melted[r][c] = true;
						cheeseCount++;
					}
				}
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (melted[r][c]) {
						board[r][c] = air;
						melted[r][c] = false;
					}
				}
			}
			if (cheeseCount == 0) {
				break;
			}
			prevCount = cheeseCount;
			air++;
		}
		System.out.println(count - 1);
		System.out.println(prevCount);
	}
	
	
	public static boolean melt(int r, int c, int air) {
		if (board[r][c] != CHEESE) {
			return false;
		}
		for (int d = 0; d < 4; d++) {
			int newR = r + dr[d];
			int newC = c + dc[d];
			if (newR >= N || newR < 0 || newC >= M || newC < 0) {
				continue;
			}
			if (board[newR][newC] == air) {
				return true;
			}
		}
		return false;
	}
}