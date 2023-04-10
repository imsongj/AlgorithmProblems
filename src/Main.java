import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 25331 Drop7
 * 
 */

public class Main {		
	static final int N = 7;
	static final int EMPTY = 0;
	static int[][] board;
	static int[][] copyBoard;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[N][N];
		copyBoard = new int[N][N];
		String[] input;
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(input[j]);
			}
		}
		for (int r = 0; r < N; r++) {
			copyBoard[r] = Arrays.copyOf(board[r], N);
		}
		int number = Integer.parseInt(br.readLine());
		/*for (int c = 0; c < N; c++) { // 떨어뜨리는 위치
			copyBoard[0][c] = number;
			gravity(c);
		}*/
		copyBoard[0][2] = number;
		gravity(2);
		checkRow(6);
		for (int c = 0; c < N; c++) { // 떨어뜨리는 위치
			System.out.println(Arrays.toString(copyBoard[c]));
		}
		
	}
	
	public static void remove() {
		for (int r = 0; r < N; r++) {
			
		}
	}
	
	public static void checkRow(int r) {
		int length = 0;
		int start = 0;
		for (int c = 0; c < N; c++) {
			if (copyBoard[r][c] != EMPTY) {
				length++;
				continue;
			}
			removeRow(r, start, length);
			start = c + 1;
		}
		removeRow(r, start, length);
	}
	
	public static void removeRow(int r, int start, int length) {
		boolean changed = false;
		for (int j = start; j < length; j++) {
			if (copyBoard[r][j] == length) {
				copyBoard[r][j] = EMPTY;
				gravity(j);
				changed = true;
			}
		}
		if (changed) {
			checkRow(r);
		}
		
	}
	public static void gravity(int c) {
		for (int r = N - 2; r >= 0; r--) {
			if (copyBoard[r][c] == EMPTY) {
				continue;
			}
			int i = r + 1;
			while (i < N) {
				
				if (copyBoard[i][c] != EMPTY) {
					break;
				}
				copyBoard[i][c] = copyBoard[i - 1][c];
				copyBoard[i - 1][c] = EMPTY;
				i++;
			}
		}
	}
}
