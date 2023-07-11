import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * 14500 테트로미노
 * 
 * 
 */
public class main_14500_테트로미노 {	
	public static final int MAX_LENGTH = 4;
	public static final int[] dr = {0, 0, -1, 1};
	public static final int[] dc = {-1, 1, 0, 0};
	
	public static final int[][] tr = {{0, 0, 0, 1}, {1, 1, 1, 0}, {0, 1, 2, 1}, {0, 1, 2, 1}};
	public static final int[][] tc = {{0, 1, 2, 1}, {0, 1, 2, 1}, {0, 0, 0, 1}, {1, 1, 1, 0}};
	public static int N;
	public static int M;
	public static int maxValue = 0;
	public static int[][] board;
	public static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		board = new int[N][M];
		visited = new boolean[N][M];
		
		for (int r = 0; r < N; r++) {
			input = br.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(input[c]);
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				visited[r][c] = true;
				dfs(r, c, 1, board[r][c]);
				visited[r][c] = false;
				checkT(r, c);
			}
		}
		System.out.println(maxValue);
	}
	
	public static void dfs(int startR, int startC, int length, int sum) {
		if (length == MAX_LENGTH) {
			if (sum > maxValue) {
				maxValue = sum;
			}
			return;
		}
		//System.out.println(startR + " " + startC + " " + length + " " + sum);
		for (int d = 0; d < 4; d++) {
			int newR = startR + dr[d];
			int newC = startC + dc[d];
			if (newR < 0 || newR >= N || newC < 0 || newC >= M) {
				continue;
			}
			if (visited[newR][newC]) {
				continue;
			}
			visited[newR][newC] = true;
			dfs(newR, newC, length + 1, sum + board[newR][newC]);
			visited[newR][newC] = false;
		}
	}
	
	public static void checkT(int startR, int startC) {
		outer:
		for (int i = 0; i < 4; i++) {
			int sum = 0;
			for (int d = 0; d < 4; d++) {
				int newR = startR + tr[i][d];
				int newC = startC + tc[i][d];
				if (newR < 0 || newR >= N || newC < 0 || newC >= M) {
					continue outer;
				}
				sum += board[newR][newC];
			}
			if (sum > maxValue) {
				maxValue = sum;
			}
		}
	}
}