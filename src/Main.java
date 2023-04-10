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
	static int[][] originalBoard;
	static int[][] copyBoard;
	static boolean[][] removed;
	static int minCount = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		originalBoard = new int[N][N];
		copyBoard = new int[N][N];
		
		String[] input;
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				originalBoard[i][j] = Integer.parseInt(input[j]);
			}
		}
		int number = Integer.parseInt(br.readLine());
		for (int c = 0; c < N; c++) { // 떨어뜨리는 위치
			for (int r = 0; r < N; r++) {
				copyBoard[r] = Arrays.copyOf(originalBoard[r], N);
			}
			copyBoard[0][c] = number;
			gravity(c);
			do {
				removed = new boolean[N][N];
				handleCols();
				handleRows();
			} while (remove());
			minCount = Math.min(minCount, countLeftOver());
		}
		System.out.println(minCount);
	}
	public static int countLeftOver() {
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (copyBoard[r][c] != EMPTY) {
					count++;
				}
			}
		}
		return count;
	}
	public static boolean remove() {
		boolean isRemoved = false;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (removed[r][c]) {
					copyBoard[r][c] = EMPTY;
					isRemoved = true;
				}
			}
		}
		if (isRemoved) {
			applyGravity();
		}
		return isRemoved;
	}
	
	public static void applyGravity() {
		for (int c = 0; c < N; c++) {
			gravity(c);
		}
	}
	public static void handleCols() {
		for (int c = 0; c < N; c++) {
			checkCol(c);
		}
	}
	public static void checkCol(int c) {
		int length = 0;
		for (int r = 0; r < N; r++) {
			if (copyBoard[r][c] != EMPTY) {
				length++;
				continue;
			}
		}
		for (int i = N - 1; i > N - 1 - length; i--) {
			if (copyBoard[i][c] == length) {
				removed[i][c] = true;
			}
		}
	}
	public static void handleRows() {
		for (int r = 0; r < N; r++) {
			checkRow(r);
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
			markRow(r, start, length);
			start = c + 1;
		}
		markRow(r, start, length);
	}
	
	public static void markRow(int r, int start, int length) {
		for (int j = start; j < length; j++) {
			if (copyBoard[r][j] == length) {
				removed[r][j] = true;
			}
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
