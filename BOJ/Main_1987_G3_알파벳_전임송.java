import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1987_G3_알파벳_전임송 {
	static final int NUMBER_OF_ALPHABETS = 26;
	
	static int row;
	static int col;
	static char[][] board;
	static boolean[] alphabet;
	static int maxDepth = 0;
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		row = Integer.parseInt(input[0]);
		col = Integer.parseInt(input[1]);
		
		board = new char[row][col];
		alphabet = new boolean[NUMBER_OF_ALPHABETS];
		String line;
		for (int r = 0; r < row; r++) {
			line = br.readLine();
			for (int c = 0; c < col; c++) {
				board[r][c] = line.charAt(c);
			}
		}
		
		alphabet[board[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		
		System.out.println(maxDepth);
	}
	
	public static void dfs(int r, int c, int depth) {
		for (int d = 0; d < 4; d++) {
			int newR = r + dr[d];
			int newC = c + dc[d];
			if (newR < 0 || newR >= row || newC < 0 || newC >= col) {
				continue;
			}
			if (alphabet[board[newR][newC] - 'A']) {
				maxDepth = Math.max(maxDepth, depth);
				continue;
			}
			alphabet[board[newR][newC] - 'A'] = true;
			dfs(newR, newC, depth + 1);
			alphabet[board[newR][newC] - 'A'] = false;
		}
	}
}
