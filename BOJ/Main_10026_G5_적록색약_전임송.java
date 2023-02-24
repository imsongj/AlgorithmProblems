import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//10026 적록색약

//dfs사용 완전 탐색

public class Main_10026_G5_적록색약_전임송 {
	static final char RED = 'R';
	static final char BLUE = 'B';
	static final char GREEN = 'G';
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	static int N;
	static char board[][];
	static boolean visited[][];
	static int count;
	static int colorBlindCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		String line;
		for (int r = 0; r < N; r++) {
			line = br.readLine();
			for (int c = 0; c < N; c++) {
				board[r][c] = line.charAt(c);
			}
		}
		System.out.printf("%d %d%n", findArea(false), findArea(true));
	}
	
	public static int findArea(boolean isColorBlind) {
		visited = new boolean[N][N];
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c]) {
					continue;
				}
				count++;
				dfs(r, c, board[r][c], isColorBlind);
			}
		}
		return count;
	}

	public static void dfs(int r, int c, char color, boolean isColorBlind) {
		visited[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int newR = r + dr[d];
			int newC = c + dc[d];
			if (newR < 0 || newR >= N || newC < 0 || newC >= N) {
				continue;
			}
			if (visited[newR][newC]) {
				continue;
			}
			if (!isColorBlind && board[newR][newC] != color) {
				continue;
			}
			if (isColorBlind && board[newR][newC] != color) {
				if (color == BLUE || board[newR][newC] == BLUE) {
					continue;
				}
			}
			dfs(newR, newC, color, isColorBlind);
		}
	}
}
