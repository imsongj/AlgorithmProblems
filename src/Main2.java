import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//2239 스도쿠
/*
 * 행 9개 빝
 */
public class Main2 {
	static final int N = 9;
	static int[][] board;
	static boolean[][] rows;
	static boolean[][] cols;
	static boolean[][] squares;
	static boolean flag = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		board = new int[N][N];
		rows = new boolean[N][N + 1];
		cols = new boolean[N][N + 1];
		squares = new boolean[N][N + 1];
		for (int r = 0; r < N; r++) {
			line = br.readLine();
			for (int c = 0; c < N; c++) {
				int number = line.charAt(c) - '0';
				//System.out.println(number);
				board[r][c] = number;
				rows[r][number] = true;
				cols[c][number] = true;
				squares[getSquare(r, c)][number] = true;
			}
		}
		dfs(0, 0);
	}	
	
	public static void dfs(int r, int c) {
		//System.out.println(r + " " + c);
		if (flag) {
			return;
		}
		if (r == N - 1 && c == N - 1) {// 끝까지 온 경우
			printBoard();
			flag = true;
			return;
		}
		int nextR = r;
		int nextC = c + 1;
		if (nextC == N) {
			nextR++;
			nextC = 0;
		}
		if (board[r][c] != 0) {
			dfs(nextR, nextC);
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (rows[r][i]) {
				continue;
			}
			if (cols[c][i]) {
				continue;
			}
			if (squares[getSquare(r, c)][i]) {
				continue;
			}
			board[r][c] = i;
			rows[r][i] = true;
			cols[c][i] = true;
			squares[getSquare(r, c)][i] = true;
			
			dfs(nextR, nextC);
			board[r][c] = 0;
			rows[r][i] = false;
			cols[c][i] = false;
			squares[getSquare(r, c)][i] = false;
		}
	}
	
	public static int getSquare(int r, int c) {
		return (r / 3) * 3 + c / 3;
	}
	
	public static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sb.append(board[r][c]);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}
