import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1799_비숍 {
	/*
	1799 비숍
	검은칸 흰칸 분리
	 */

	static final int[] dr = { -1, -1, 1, 1 };
	static final int[] dc = { -1, 1, -1, 1 };
	static int N;
	static int[][] board;
	static int[][] original;
	static int maxWhite;
	static int maxBlack;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		original = new int[N][N];
		maxWhite = 0;
		maxBlack = 0;
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				int number =  Integer.parseInt(input[j]);
				board[i][j] = 0;
				original[i][j] = number;
			}
		}
		if (N == 1) {
			System.out.println(original[0][0]);
			return;
		}
		dfs(0, 0, true);
		dfs(1, 0, false);
		System.out.println(maxWhite + maxBlack);
	}

	static void dfs(int position, int count, boolean isWhite) {
		if (position >= N * N) {
			if (isWhite) {
				if (count > maxWhite) {
					maxWhite = count;
				}
				return;
			}
			if (count > maxBlack) {
				maxBlack = count;
			}
			return;
		}

		int r = position / N;
		int c = position % N;
		int nextPosition = position + 1;
		int nr = nextPosition / N;
		int nc = nextPosition % N;
		if (isWhite) {
			while ((nr + nc) % 2 != 0) {
				nextPosition++;
				nr = nextPosition / N;
				nc = nextPosition % N;
			}
		}
		if (!isWhite) {
			while ((nr + nc) % 2 != 1) {
				nextPosition++;
				nr = nextPosition / N;
				nc = nextPosition % N;
			}
		}

		if (original[r][c] == 1 && checkBoard(r, c)) { //대각선
			board[r][c] = 1;
			dfs(nextPosition, count + 1, isWhite);
			board[r][c] = 0;
		}
		dfs(nextPosition, count, isWhite);
	}

	static void printBoard() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	//make a function to check whether the board has more than one bishop in a diagonal
	//if it has more than one bishop in a diagonal, return false
	static boolean checkBoard(int r, int c) {
		for (int d = 0; d < 2; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (board[nr][nc] == 1) {
					return false;
				}
				nr += dr[d];
				nc += dc[d];
			}
		}
		return true;
	}
}
/*
10
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1

8
1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1

5
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1

4
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1

5
1 1 0 1 1
0 1 0 0 1
1 0 1 0 1
1 0 0 0 0
1 0 1 1 1

5
0 0 0 1 0
0 0 1 0 0
0 1 0 1 0
0 0 1 0 0
0 0 0 1 0
 */