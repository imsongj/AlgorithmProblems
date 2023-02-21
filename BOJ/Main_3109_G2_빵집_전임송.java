import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_3109_G2_빵집_전임송 {
	static int row;
	static int col;
	static char[][] board;
	static boolean[] connected;
	static int count = 0;
	
	static int[] dr = {-1, 0, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		row = Integer.parseInt(input[0]);
		col = Integer.parseInt(input[1]);
		
		board = new char[row][col];
		connected = new boolean[row];
		
		String line;
		for (int r = 0; r < row; r++) {
			line = br.readLine();
			for (int c = 0; c < col; c++) {
				board[r][c] = line.charAt(c);
			}
		}
		for (int i = 0; i < row; i++) {
			dfs(i, i, 0);
		}
		System.out.println(count);
	}
	
	public static void dfs(int start, int r, int c) {
		board[r][c] = 'x';
		if (c == col - 1) {
			connected[start] = true;
			count++;
			return;
		}
		for (int d = 0; d < 3; d++) {
			int newR = r + dr[d];
			int newC = c + 1;
			if (newR < 0 || newR >= row || newC < 0 || newC >= col) {
				continue;
			}
			if (board[newR][newC] == 'x' || connected[start]) {
				continue;
			}
			dfs(start, newR, newC);
		}
	}
	
	public static void printBoard() {
		for (int i = 0; i < row; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println();
	}
}
